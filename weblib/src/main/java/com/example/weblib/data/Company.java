package com.example.weblib.data;

public class Company {

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
