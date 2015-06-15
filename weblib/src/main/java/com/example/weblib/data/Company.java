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

    public String getTitle() {
        return fullName;
    }

    public String getDetail() {
        return addressStreet;
    }
}
