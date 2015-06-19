package com.example.weblib.db;

public final class DbContract {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WTA_DataBase.db";
    public static final String SQL_DELETE_COMPANIES =
            "DROP TABLE IF EXISTS " + CompanyEntry.TABLE_NAME;
    public static final String SQL_DELETE_ALL_COMPANY_ENTRIES =
            "DELETE FROM " + CompanyEntry.TABLE_NAME;
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_COMPANIES =
            "CREATE TABLE " + CompanyEntry.TABLE_NAME + " (" +
                    CompanyEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    CompanyEntry.COLUMN_NAME_TAXNUMBER + INTEGER_TYPE + COMMA_SEP +
                    CompanyEntry.COLUMN_NAME_FULLNAME + TEXT_TYPE + COMMA_SEP +
                    CompanyEntry.COLUMN_NAME_SHORTNAME + TEXT_TYPE + COMMA_SEP +
                    CompanyEntry.COLUMN_NAME_IDNUMBER + TEXT_TYPE + COMMA_SEP +
                    CompanyEntry.COLUMN_NAME_ADDRESSPOSTNUMBER + TEXT_TYPE + COMMA_SEP +
                    CompanyEntry.COLUMN_NAME_ADDRESSHOUSENUMBER + TEXT_TYPE + COMMA_SEP +
                    CompanyEntry.COLUMN_NAME_ADDRESSMUNICIPALITY + TEXT_TYPE + COMMA_SEP +
                    CompanyEntry.COLUMN_NAME_ADDRESSPOST + TEXT_TYPE + COMMA_SEP +
                    CompanyEntry.COLUMN_NAME_ADDRESSSTREET + TEXT_TYPE + COMMA_SEP +
                    CompanyEntry.COLUMN_NAME_SEARCHCOLUMN + TEXT_TYPE +
                    " )";

    public DbContract() {
    }

    public static class CommonColumns {
        public static final String COLUMN_NAME_ID = "_id";
    }

    public static abstract class CompanyEntry extends CommonColumns {

        public static final String TABLE_NAME = "company";

        public static final String COLUMN_NAME_TAXNUMBER = "taxnumber";
        public static final String COLUMN_NAME_FULLNAME = "fullname";
        public static final String COLUMN_NAME_SHORTNAME = "shortname";
        public static final String COLUMN_NAME_IDNUMBER = "idnumber";
        public static final String COLUMN_NAME_ADDRESSPOSTNUMBER = "addresspostnumber";
        public static final String COLUMN_NAME_ADDRESSHOUSENUMBER = "addresshousenumber";
        public static final String COLUMN_NAME_ADDRESSMUNICIPALITY = "addressmunicipality";
        public static final String COLUMN_NAME_ADDRESSPOST = "addresspost";
        public static final String COLUMN_NAME_ADDRESSSTREET = "addressstreet";
        public static final String COLUMN_NAME_SEARCHCOLUMN = "searchcolumn";
    }
}
