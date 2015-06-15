package com.example.weblib.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class Data implements DataInterface {

    @Override
    public void execute(boolean doParsing) {
        if (doParsing) {
            getObject();
        } else {
            getString();
        }
    }

    public ArrayList<Company> parse(JSONObject object) {
        ArrayList<Company> companies = new ArrayList();
        if (object != null) {
            try {
                long count = object.getLong("count");
                if (count > 0) {
                    JSONArray jsonResults = object.getJSONArray("results");
                    for (int i = 0; i < jsonResults.length(); i++) {
                        JSONObject jo = jsonResults.getJSONObject(i);

                        Company c = new Company();
                        c.taxNumber = jo.getInt("tax_number");
                        c.fullName = jo.getString("full_name");
                        c.shortName = jo.getString("short_name");
                        c.idNumber = jo.getString("id_number");
                        c.addressPostNumber = jo.getString("address_post_num");
                        c.addressHouseNumber = jo.getString("address_house_num");
                        c.addressMunicipality = jo.getString("address_municipality");
                        c.addressPost = jo.getString("address_post");
                        c.addressStreet = jo.getString("address_street");
                        c.searchColumn = jo.getString("search_column");

                        companies.add(c);
                    }
                }
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }
        return companies;
    }

    public ArrayList<Company> parse(String response) {
        JSONObject object = null;
        try {
            object = new JSONObject(response);
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return parse(object);
    }
}
