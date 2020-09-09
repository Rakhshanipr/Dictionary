package com.example.dictionary.model;

import java.util.UUID;

public class Word {

    UUID mUUID;
    String mEnglish;
    String mPersian;

    public Word(UUID uuid, String english, String persian) {
        mUUID=uuid;
        mEnglish = english;
        mPersian = persian;
    }

    public Word(String english, String persian) {
        mUUID=UUID.randomUUID();
        mEnglish = english;
        mPersian = persian;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public String getEnglish() {
        return mEnglish;
    }

    public void setEnglish(String english) {
        mEnglish = english;
    }

    public String getPersian() {
        return mPersian;
    }

    public void setPersian(String persian) {
        mPersian = persian;
    }
}
