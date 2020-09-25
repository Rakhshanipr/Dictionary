package com.example.dictionary.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionary.R;
import com.example.dictionary.model.Word;
import com.example.dictionary.repository.DictionaryRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.RowId;
import java.util.List;

public class ListWrodFragment extends Fragment {
    public static final String TAG_LIST_WORD_FRAGMENT_TO_ADD_FRAGMENT = "ListWordFragmentToAddFragment";
    public static final int REQUEST_CODE_SHOW_ADD_FRAGMENT = 0;
    public static final String TAG = "ListWordFragment";
    //region deind variable
    RecyclerView mRecyclerView;
    FloatingActionButton mFButtonAdd;
    DictionaryRepository mRepository;
    WordListAdapter mWordListAdapter;

    String mSearchText;
    //endregion

    //region defind static method and variable
    public static final String ARG_SEARCH_WORD = "searchWord";

    public static ListWrodFragment newInstance(String searchWord) {
        ListWrodFragment fragment = new ListWrodFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SEARCH_WORD, searchWord);
        fragment.setArguments(args);
        return fragment;
    }

    //endregion
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = DictionaryRepository.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_wrod, container, false);
        findViews(view);
        initViews();
        setListners();
        return view;

    }

    private void setListners() {
        mFButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddWordFragment addWordFragment = AddWordFragment.newInstance();
                addWordFragment.setTargetFragment(ListWrodFragment.this, REQUEST_CODE_SHOW_ADD_FRAGMENT);
                addWordFragment.show(getFragmentManager(), TAG_LIST_WORD_FRAGMENT_TO_ADD_FRAGMENT);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null || resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == REQUEST_CODE_SHOW_ADD_FRAGMENT) {
            mWordListAdapter.setWordList(mRepository.getLists(""));
            mWordListAdapter.notifyDataSetChanged();
        }
    }


    public void refList(String search) {
        if (search == null) {
            search="";
        } else
            mSearchText = search;
        mWordListAdapter.setWordList(mRepository.getLists(search));
        mWordListAdapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), search, Toast.LENGTH_SHORT).show();
    }


    private void initViews() {
        List<Word> list = mRepository.getLists("");
        mWordListAdapter = new WordListAdapter(list);
        mRecyclerView.setAdapter(mWordListAdapter);
    }

    private void findViews(View view) {
        mFButtonAdd = view.findViewById(R.id.fButton_fragmentListWord_add);
        mRecyclerView = view.findViewById(R.id.fragmentListWord_recyclerView_wordList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        mWordListAdapter.notifyDataSetChanged();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {

        Word mWord;
        TextView mTextViewEnglish;
        TextView mTextViewPersian;
        ImageButton mImageButtonDelete;
        ImageButton mImageButtonEdit;
        ImageButton mImageButtonShare;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewEnglish = itemView.findViewById(R.id.textView_rowDictionary_english);
            mTextViewPersian = itemView.findViewById(R.id.textView_rowDictionary_persian);
            mImageButtonDelete = itemView.findViewById(R.id.imageButton_rowDictionary_delete);
            mImageButtonEdit = itemView.findViewById(R.id.imageButton_rowDictionary_edit);
            mImageButtonShare = itemView.findViewById(R.id.imageButton_rowDictionary_share);
            setListnersRowDictionary();
        }

        private void setListnersRowDictionary() {
            mImageButtonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mRepository.delete(mWord);
                    refList(mSearchText);
                }
            });

            mImageButtonShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: set share code
                }
            });

            mImageButtonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO:set Edit code
                }
            });
        }

        public void bind(Word word) {
            mWord = word;
            mTextViewEnglish.setText(word.getEnglish());
            mTextViewPersian.setText(word.getPersian());
        }

    }


    public class WordListAdapter extends RecyclerView.Adapter<WordViewHolder> {

        List<Word> mWordList;


        @NonNull
        @Override
        public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext())
                    .inflate(R.layout.row_dictionary, parent, false);

            return new WordViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
            holder.bind(mWordList.get(position));
        }

        @Override
        public int getItemCount() {
            return mWordList.size();
        }

        public WordListAdapter(List<Word> wordList) {
            mWordList = wordList;
        }

        public List<Word> getWordList() {
            return mWordList;
        }

        public void setWordList(List<Word> wordList) {
            mWordList = wordList;
        }
    }

}