package com.example.dictionary.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dictionary.R;
import com.example.dictionary.model.Word;
import com.example.dictionary.repository.DictionaryRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.RowId;
import java.util.List;

public class ListWrodFragment extends Fragment {
    public static final String TAG_LIST_WORD_FRAGMENT_TO_ADD_FRAGMENT = "ListWordFragmentToAddFragment";
    //region deind variable
    RecyclerView mRecyclerView;
    FloatingActionButton mFButtonAdd;
    DictionaryRepository mRepository;
    WordListAdapter mWordListAdapter;
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
        mRepository=DictionaryRepository.getInstance(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_wrod, container, false);
        initViews();
        findViews(view);
        setListners();
        return view;

    }

    private void setListners() {
        mFButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddWordFragment addWordFragment=AddWordFragment.newInstance();
                addWordFragment.show(getFragmentManager(), TAG_LIST_WORD_FRAGMENT_TO_ADD_FRAGMENT);
            }
        });
    }

    private void initViews() {
        mWordListAdapter=new WordListAdapter(mRepository.getLists(""));
        mRecyclerView.setAdapter(mWordListAdapter);
    }

    private void findViews(View view) {
        mFButtonAdd = view.findViewById(R.id.fButton_fragmentListWord_add);
        mRecyclerView = view.findViewById(R.id.fragmentListWord_recyclerView_wordList);
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewEnglish;
        TextView mTextViewPersian;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewEnglish = itemView.findViewById(R.id.textView_rowDictionary_english);
            mTextViewPersian = itemView.findViewById(R.id.textView_rowDictionary_persian);
        }

        public void bind(Word word) {
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