package com.example.dictionary.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import com.example.dictionary.database.DictionaryBaseHelper;
import com.example.dictionary.database.DictionaryDBSchema;
import com.example.dictionary.database.cursorwrapper.WordCursorWrapper;
import com.example.dictionary.model.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DictionaryRepository implements IRepository<Word> {

    //region defind variable
    private SQLiteDatabase mDatabase;
    //endregion

    //region defind static method and variable
    public static DictionaryRepository mDictionaryRepository;
    public static Context mContext;
    //endregion

    public static DictionaryRepository getInstance(Context context) {
        if (mDictionaryRepository == null)
            mDictionaryRepository = new DictionaryRepository(context);
        return mDictionaryRepository;
    }

    private DictionaryRepository(Context context) {
        mContext = context.getApplicationContext();
        DictionaryBaseHelper dictionaryBaseHelper = new DictionaryBaseHelper(mContext);
        mDatabase = dictionaryBaseHelper.getWritableDatabase();
    }

    @Override
    public Word get(UUID uuid) {
        String selection = DictionaryDBSchema.WordTable.COLS.UUID + "=?";
        String[] selectionArgs = {uuid.toString()};
        WordCursorWrapper cursorWrapper = queryWord(selection, selectionArgs);
        if (cursorWrapper == null || cursorWrapper.getColumnCount() == 0)
            return null;
        try {
            cursorWrapper.moveToFirst();
            return cursorWrapper.getWord();
        } finally {
            cursorWrapper.close();
        }

    }

    @Override
    public void update(Word word) {

        String selection = DictionaryDBSchema.WordTable.COLS.UUID + "=?";
        String[] selectionArgs = {word.getUUID().toString()};
        mDatabase.update(DictionaryDBSchema.WordTable.NAME, getContentValueFromWrod(word), selection, selectionArgs);

    }


    @Override
    public List<Word> getLists(String search) {
        List<Word> list = new ArrayList<>();
        String selection = DictionaryDBSchema.WordTable.COLS.ENGLISH + " like '' or " + DictionaryDBSchema.WordTable.COLS.PERSIAN + " like ''";
        String[] selectionArgs = {};
        WordCursorWrapper cursorWrapper = queryWord(selection, selectionArgs);
        if (cursorWrapper == null || cursorWrapper.getColumnCount() == 0)
            return null;
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                list.add(cursorWrapper.getWord());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return list;
    }

    @Override
    public void delete(Word word) {

        String selection = DictionaryDBSchema.WordTable.COLS.UUID + "=?";
        String[] selectionArgs = {word.getUUID().toString()};
        mDatabase.delete(DictionaryDBSchema.WordTable.NAME, selection, selectionArgs);

    }

    @Override
    public void insert(Word word) {
        mDatabase.insert(DictionaryDBSchema.WordTable.NAME, null, getContentValueFromWrod(word));
    }


    private ContentValues getContentValueFromWrod(Word word) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DictionaryDBSchema.WordTable.COLS.UUID, word.getUUID().toString());
        contentValues.put(DictionaryDBSchema.WordTable.COLS.ENGLISH, word.getEnglish());
        contentValues.put(DictionaryDBSchema.WordTable.COLS.PERSIAN, word.getPersian());

        return contentValues;
    }


    private WordCursorWrapper queryWord(String selection, String[] selectionArgs) {

        Cursor cursor = mDatabase.query(DictionaryDBSchema.WordTable.NAME
                , null
                , selection
                , selectionArgs
                , null
                , null
                , null);

        return new WordCursorWrapper(cursor);

    }

}
