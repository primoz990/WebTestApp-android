package com.example.weblib.data;

import org.json.JSONObject;

import java.util.ArrayList;

public interface DataInterface {

    String URL = "http://ajpes.intera.si/index/search?search=frizer&type=title";

    void execute();

    ArrayList<Company> parse(JSONObject object);

    ArrayList<Company> parse(String response);

    void setToDB(ArrayList<Company> companies);

    void getFromDB();

    boolean save(ArrayList<Company> companies);

    void getObject();

    void getFromWeb();

    void onError(String message);

    void onSuccess(ArrayList<Company> companies, boolean cached);
}
