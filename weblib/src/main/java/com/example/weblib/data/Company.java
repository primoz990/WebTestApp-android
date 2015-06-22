package com.example.weblib.data;

import com.example.weblib.db.DbObject;

public class Company extends DbObject {

    public static final String SQL_COLUMN_NAME_TAXNUMBER = "taxnumber";
    public static final String SQL_COLUMN_NAME_FULLNAME = "fullname";
    public static final String SQL_COLUMN_NAME_SHORTNAME = "shortname";
    public static final String SQL_COLUMN_NAME_IDNUMBER = "idnumber";
    public static final String SQL_COLUMN_NAME_ADDRESSPOSTNUMBER = "addresspostnumber";
    public static final String SQL_COLUMN_NAME_ADDRESSHOUSENUMBER = "addresshousenumber";
    public static final String SQL_COLUMN_NAME_ADDRESSMUNICIPALITY = "addressmunicipality";
    public static final String SQL_COLUMN_NAME_ADDRESSPOST = "addresspost";
    public static final String SQL_COLUMN_NAME_ADDRESSSTREET = "addressstreet";
    public static final String SQL_COLUMN_NAME_SEARCHCOLUMN = "searchcolumn";

    public int taxNumber;
    public String fullName;
    public String shortName;
    public String idNumber;
    public String addressPostNumber;
    public String addressHouseNumber;
    public String addressMunicipality;
    public String addressPost;
    public String addressStreet;
    public String searchColumn;

    public Company() {
        this.taxNumber = -1;
        this.fullName = "";
        this.shortName = "";
        this.idNumber = "";
        this.addressPostNumber = "";
        this.addressHouseNumber = "";
        this.addressMunicipality = "";
        this.addressPost = "";
        this.addressStreet = "";
        this.searchColumn = "";
    }

    public String[] getColumns() {
        return new String[]{
                SQL_COLUMN_NAME_TAXNUMBER,
                SQL_COLUMN_NAME_FULLNAME,
                SQL_COLUMN_NAME_SHORTNAME,
                SQL_COLUMN_NAME_IDNUMBER,
                SQL_COLUMN_NAME_ADDRESSPOSTNUMBER,
                SQL_COLUMN_NAME_ADDRESSHOUSENUMBER,
                SQL_COLUMN_NAME_ADDRESSMUNICIPALITY,
                SQL_COLUMN_NAME_ADDRESSPOST,
                SQL_COLUMN_NAME_ADDRESSSTREET,
                SQL_COLUMN_NAME_SEARCHCOLUMN
        };
    }

    public String[] getValues() {
        return new String[]{
                Integer.toString(this.taxNumber),
                toSqlString(this.fullName),
                toSqlString(this.shortName),
                toSqlString(this.idNumber),
                toSqlString(this.addressPostNumber),
                toSqlString(this.addressHouseNumber),
                toSqlString(this.addressMunicipality),
                toSqlString(this.addressPost),
                toSqlString(this.addressStreet),
                toSqlString(this.searchColumn)
        };
    }

    public String[] getTypes() {
        return new String[]{
                TYPE_INTEGER,
                TYPE_TEXT,
                TYPE_TEXT,
                TYPE_TEXT,
                TYPE_TEXT,
                TYPE_TEXT,
                TYPE_TEXT,
                TYPE_TEXT,
                TYPE_TEXT,
                TYPE_TEXT
        };
    }

    public int getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(int taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAddressPostNumber() {
        return addressPostNumber;
    }

    public void setAddressPostNumber(String addressPostNumber) {
        this.addressPostNumber = addressPostNumber;
    }

    public String getAddressHouseNumber() {
        return addressHouseNumber;
    }

    public void setAddressHouseNumber(String addressHouseNumber) {
        this.addressHouseNumber = addressHouseNumber;
    }

    public String getAddressMunicipality() {
        return addressMunicipality;
    }

    public void setAddressMunicipality(String addressMunicipality) {
        this.addressMunicipality = addressMunicipality;
    }

    public String getAddressPost() {
        return addressPost;
    }

    public void setAddressPost(String addressPost) {
        this.addressPost = addressPost;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getSearchColumn() {
        return searchColumn;
    }

    public void setSearchColumn(String searchColumn) {
        this.searchColumn = searchColumn;
    }
}
