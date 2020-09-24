package com.example.dictionary.database.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.dictionary.database.DictionaryDBSchema.WordTable.COLS;
import com.example.dictionary.model.Word;


import java.util.UUID;

public class WordCursorWrapper extends CursorWrapper {

    public WordCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Word getWord(){
        UUID uuid=UUID.fromString(getString(getColumnIndex(COLS.UUID)));
        String english=getString(getColumnIndex(COLS.ENGLISH));
        String persian=getString(getColumnIndex(COLS.PERSIAN));
        Word word=new Word(uuid,english,persian);
        return word;
    }
}
