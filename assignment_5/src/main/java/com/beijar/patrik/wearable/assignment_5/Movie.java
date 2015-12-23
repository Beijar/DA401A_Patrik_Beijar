package com.beijar.patrik.wearable.assignment_5;


import java.io.Serializable;

public class Movie implements Serializable {
    private String mTitle;
    private String mYear;
    private String mInfo;
    private int mThumbn;
    private int mFanArt;

    public Movie(String title, String year, String info, int thumnbn, int fanArt) {
        mTitle = title;
        mYear = year;
        mInfo = info;
        mThumbn = thumnbn;
        mFanArt = fanArt;
    }

    public int getmThumbn() {
        return mThumbn;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmYear() {
        return mYear;
    }

    public String getmInfo() {
        return mInfo;
    }

    public int getmFanArt() {
        return mFanArt;
    }
}
