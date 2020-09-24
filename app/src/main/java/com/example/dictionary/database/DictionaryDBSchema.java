package com.example.dictionary.database;

public class DictionaryDBSchema {
    public static final String NAME = "DictionaryDB.db";
    public static final int VERSION = 1;

    public static final class WordTable {

        public static final String NAME = "WordTable";

        public static final class COLS {
            public static final String ID = "id";
            public static final String UUID = "uuid";
            public static final String ENGLISH = "english";
            public static final String PERSIAN = "persian";
        }

    }
}
