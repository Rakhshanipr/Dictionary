package com.example.dictionary.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.dictionary.R;
import com.example.dictionary.controller.fragment.ListWrodFragment;
import com.example.dictionary.controller.fragment.SearchFragment;

public class ViewWordsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_word);
        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.container_list);
        if (fragment==null){
            fragmentManager
                    .beginTransaction()
                    .add(R.id.container_list,ListWrodFragment.newInstance(""))
                    .add(R.id.container_search, SearchFragment.newInstance())
                    .commit();
        }
    }
}