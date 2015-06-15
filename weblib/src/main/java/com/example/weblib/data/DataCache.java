package com.example.weblib.data;

import java.util.ArrayList;

public class DataCache {

    public static final long MAX_AGE_MS = 60 * 1000;
    private static DataCache mInstance = null;
    private long mTime;
    private ArrayList<Company> mCompanies;


    private DataCache() {
        mCompanies = null;
        mTime = 0;
    }

    public static DataCache getInstance() {
        if (mInstance == null) {
            mInstance = new DataCache();
        }
        return mInstance;
    }

    public ArrayList<Company> getCompanies() {
        if (mCompanies == null) {
            mCompanies = new ArrayList<>();
        }
        return mCompanies;
    }

    public boolean setCompanies(ArrayList<Company> data) {
        if (data != null) {
            mCompanies = data;
            mTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    public long getAgeMs() {
        long now = System.currentTimeMillis();
        return now - mTime;
    }

    public boolean hasData() {
        if (mCompanies != null && mCompanies.size() > 0) {
            return true;
        } else {
            return false;
        }
    }


}
