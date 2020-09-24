package com.example.dictionary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.dictionary.database.DictionaryDBSchema.*;
import com.example.dictionary.database.DictionaryDBSchema.WordTable.*;
import androidx.annotation.Nullable;

public class DictionaryBaseHelper extends SQLiteOpenHelper {
    public DictionaryBaseHelper(@Nullable Context context) {
        super(context, DictionaryDBSchema.NAME, null, DictionaryDBSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + WordTable.NAME + "(" +
                COLS.ID + " integer primary key autoincrement," +
                COLS.UUID + " text," +
                COLS.ENGLISH + " text," +
                COLS.PERSIAN + " text " +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
