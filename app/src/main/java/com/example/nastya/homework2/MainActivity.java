package com.example.nastya.homework2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String BUTTON_IS_ENABLED = "is_enabled";
    private MenuItem item_delete;
    private boolean button_enabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            button_enabled = savedInstanceState.getBoolean(BUTTON_IS_ENABLED);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        item_delete = menu.findItem(R.id.delete);
        item_delete.setEnabled(button_enabled);
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
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            item_delete.setEnabled(true);
        }
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.add, R.animator.delete)
                .add(R.id.container, DocumentFragment.newInstance(getSupportFragmentManager().getBackStackEntryCount()))
                .addToBackStack(null)
                .commit();
    }

    //удаление фрагмента
    private void deleteDocument() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            item_delete.setEnabled(false);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(BUTTON_IS_ENABLED, item_delete.isEnabled());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            item_delete.setEnabled(false);
        }
        super.onBackPressed();
    }
}
