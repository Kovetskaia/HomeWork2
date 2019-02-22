package com.example.nastya.homework2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private static final String BUTTON_IS_ENABLED = "is_enabled";
    private MenuItem itemDelete;
    private boolean itemDeleteIsEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().addOnBackStackChangedListener(this);

        if (savedInstanceState != null) {
            itemDeleteIsEnabled = savedInstanceState.getBoolean(BUTTON_IS_ENABLED);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        itemDelete = menu.findItem(R.id.delete);
        itemDelete.setEnabled(itemDeleteIsEnabled);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case (R.id.add):
                addDocument();
                return true;
            case (R.id.delete):
                deleteDocument();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    //добавление нового фрагмента
    private void addDocument() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.add, R.animator.delete)
                .replace(R.id.container, DocumentFragment.newInstance(getSupportFragmentManager().getBackStackEntryCount()))
                .addToBackStack(null)
                .commit();
    }

    //удаление фрагмента
    private void deleteDocument() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onBackStackChanged() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount == 1 && !itemDelete.isEnabled()) {
            itemDelete.setEnabled(true);
        }
        if (backStackEntryCount == 0) {
            itemDelete.setEnabled(false);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(BUTTON_IS_ENABLED, itemDelete.isEnabled());
        super.onSaveInstanceState(outState);
    }
}
