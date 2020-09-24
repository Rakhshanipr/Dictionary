package com.example.dictionary.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dictionary.R;
import com.example.dictionary.model.Word;
import com.example.dictionary.repository.DictionaryRepository;

public class AddWordFragment extends DialogFragment {

    //region defind variable
    Button mButtonSave;
    Button mButtonCancel;

    EditText mEditTextEnglish;
    EditText mEditTextPersian;

    DictionaryRepository mRepository;
    //endregion


    public static AddWordFragment newInstance() {
        AddWordFragment fragment = new AddWordFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository=DictionaryRepository.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_word, container, false);
        findViews(view);
        setListners();
        return view;
    }

    private void setListners() {
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word=new Word(mEditTextEnglish.getText().toString(),
                        mEditTextPersian.getText().toString());
                mRepository.insert(word);
                getDialog().dismiss();
            }
        });

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }

    private void findViews(View view) {
        mButtonSave=view.findViewById(R.id.button_fragmentAddWord_save);
        mButtonCancel=view.findViewById(R.id.button_fragmentAddWord_cancel);

        mEditTextEnglish=view.findViewById(R.id.editText_fragmentAddWord_english);
        mEditTextPersian=view.findViewById(R.id.editText_fragmentAddWord_persian);
    }
}