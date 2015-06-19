package com.example.webtestapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weblib.data.Company;
import com.example.weblib.data.Data;
import com.example.weblib.db.DbContract;
import com.example.webtestapp.data.db.DbHelper;

import org.json.JSONObject;

import java.util.ArrayList;

public abstract class DataHelper extends Data {

    Context context;

    public DataHelper(Context context) {
        this.context = context;
    }


    @Override
    public void getFromWeb() {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<Company> companies = parse(response);//TODO parse in backgroud thread
                        if (companies.size() > 0) {
                            save(companies);
                        }
                        onSuccess(companies, false);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        onError(error.toString());
                    }
                });
        queue.add(jsObjRequest);
    }

    @Override
    public boolean setToDB(final ArrayList<Company> companies) {
        new Thread(new Runnable() {
            public void run() {
                DbHelper dbHelper = new DbHelper(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL(DbContract.SQL_DELETE_ALL_COMPANY_ENTRIES);
                if (companies != null && companies.size() > 0) {
                    for (int i = 0; i < companies.size(); i++) {
                        Company c = companies.get(i);
                        if (c != null) {
                            // Create a new map of values, where column names are the keys
                            ContentValues values = new ContentValues();
                            values.put(DbContract.CompanyEntry.COLUMN_NAME_ID, i);
                            values.put(DbContract.CompanyEntry.COLUMN_NAME_TAXNUMBER, c.taxNumber);
                            values.put(DbContract.CompanyEntry.COLUMN_NAME_FULLNAME, c.fullName);
                            values.put(DbContract.CompanyEntry.COLUMN_NAME_SHORTNAME, c.shortName);
                            values.put(DbContract.CompanyEntry.COLUMN_NAME_IDNUMBER, c.idNumber);
                            values.put(DbContract.CompanyEntry.COLUMN_NAME_ADDRESSPOSTNUMBER, c.addressPostNumber);
                            values.put(DbContract.CompanyEntry.COLUMN_NAME_ADDRESSHOUSENUMBER, c.addressHouseNumber);
                            values.put(DbContract.CompanyEntry.COLUMN_NAME_ADDRESSMUNICIPALITY, c.addressMunicipality);
                            values.put(DbContract.CompanyEntry.COLUMN_NAME_ADDRESSPOST, c.addressPost);
                            values.put(DbContract.CompanyEntry.COLUMN_NAME_ADDRESSSTREET, c.addressStreet);
                            values.put(DbContract.CompanyEntry.COLUMN_NAME_SEARCHCOLUMN, c.searchColumn);

                            // Insert the new row, returning the primary key value of the new row
                            long newRowId = db.insert(
                                    DbContract.CompanyEntry.TABLE_NAME,
                                    null,
                                    values);
                            Log.d("ROW insert", newRowId + "");
                        } else {
                            Log.d("ROW insert FAILED", "Object is NULL");
                        }
                    }
                }
            }
        }).start();
        return false;
    }

    @Override
    public ArrayList<Company> getFromDB() {
        //TODO in background thread
        ArrayList<Company> companies = new ArrayList<>();

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DbContract.CompanyEntry.COLUMN_NAME_ID,
                DbContract.CompanyEntry.COLUMN_NAME_TAXNUMBER,
                DbContract.CompanyEntry.COLUMN_NAME_FULLNAME,
                DbContract.CompanyEntry.COLUMN_NAME_SHORTNAME,
                DbContract.CompanyEntry.COLUMN_NAME_IDNUMBER,
                DbContract.CompanyEntry.COLUMN_NAME_ADDRESSPOSTNUMBER,
                DbContract.CompanyEntry.COLUMN_NAME_ADDRESSHOUSENUMBER,
                DbContract.CompanyEntry.COLUMN_NAME_ADDRESSMUNICIPALITY,
                DbContract.CompanyEntry.COLUMN_NAME_ADDRESSPOST,
                DbContract.CompanyEntry.COLUMN_NAME_ADDRESSSTREET,
                DbContract.CompanyEntry.COLUMN_NAME_SEARCHCOLUMN
        };

        Cursor cursor = db.query(
                DbContract.CompanyEntry.TABLE_NAME,  // The table to query
                projection,                        // The columns to return
                null,                              // The columns for the WHERE clause
                null,                              // The values for the WHERE clause
                null,                              // don't group the rows
                null,                              // don't filter by row groups
                null                               // The sort order
        );

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Company company = new Company();
                company.taxNumber = cursor.getInt(cursor.getColumnIndex(DbContract.CompanyEntry.COLUMN_NAME_TAXNUMBER));
                company.fullName = cursor.getString(cursor.getColumnIndex(DbContract.CompanyEntry.COLUMN_NAME_FULLNAME));
                company.shortName = cursor.getString(cursor.getColumnIndex(DbContract.CompanyEntry.COLUMN_NAME_SHORTNAME));
                company.idNumber = cursor.getString(cursor.getColumnIndex(DbContract.CompanyEntry.COLUMN_NAME_IDNUMBER));
                company.addressPostNumber = cursor.getString(cursor.getColumnIndex(DbContract.CompanyEntry.COLUMN_NAME_ADDRESSPOSTNUMBER));
                company.addressHouseNumber = cursor.getString(cursor.getColumnIndex(DbContract.CompanyEntry.COLUMN_NAME_ADDRESSHOUSENUMBER));
                company.addressMunicipality = cursor.getString(cursor.getColumnIndex(DbContract.CompanyEntry.COLUMN_NAME_ADDRESSMUNICIPALITY));
                company.addressPost = cursor.getString(cursor.getColumnIndex(DbContract.CompanyEntry.COLUMN_NAME_ADDRESSPOST));
                company.addressStreet = cursor.getString(cursor.getColumnIndex(DbContract.CompanyEntry.COLUMN_NAME_ADDRESSSTREET));
                company.searchColumn = cursor.getString(cursor.getColumnIndex(DbContract.CompanyEntry.COLUMN_NAME_SEARCHCOLUMN));
                companies.add(company);
            } while (cursor.moveToNext());
            cursor.close();
        }
        Log.d("DB READ", companies.size() + " companies");
        return companies;
    }
}
