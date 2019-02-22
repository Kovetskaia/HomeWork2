package com.example.nastya.homework2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DocumentFragment extends Fragment {

    private static final String ARGUMENT_DOCUMENT_NUMBER = "document_number";
    private int documentNumber;

    static DocumentFragment newInstance(int number) {
        DocumentFragment documentFragment = new DocumentFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_DOCUMENT_NUMBER, number);
        documentFragment.setArguments(arguments);
        return documentFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //получаем номер документа
        documentNumber = getArguments().getInt(ARGUMENT_DOCUMENT_NUMBER) + 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.document_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView addDocument = view.findViewById(R.id.newDocument);
        addDocument.setText(String.format(getString(R.string.document), documentNumber));

    }
}
