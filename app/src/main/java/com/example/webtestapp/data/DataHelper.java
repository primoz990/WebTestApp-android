package com.example.webtestapp.data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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
    public void setToDB(final ArrayList<Company> companies) {
        new Thread(new Runnable() {
            public void run() {
                DbHelper dbHelper = new DbHelper(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                final Company _c = new Company();
                db.execSQL(_c.getSqlDeleteEntries());
                if (companies != null && companies.size() > 0) {
                    for (int i = 0; i < companies.size(); i++) {
                        Company c = companies.get(i);
                        if (c != null) {
                            String insert = c.getSqlInsert();
                            try {
                                db.execSQL(insert);
                                Log.d("ROW insert", c.getFullName());
                            } catch (SQLException sqle) {
                                Log.e("ROW insert FAILED", "Exception");
                                sqle.printStackTrace();
                            }
                        } else {
                            Log.e("ROW insert FAILED", "Object is NULL");
                        }
                    }
                }
            }
        }).start();
    }

    @Override
    public void getFromDB() {
        //TODO in background thread
        ArrayList<Company> companies = null;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        final Company _c = new Company();
        Cursor cursor = db.rawQuery(_c.getSqlSelectAll(), null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            companies = new ArrayList<>();
            do {
                Company company = new Company();
                company.taxNumber = cursor.getInt(cursor.getColumnIndex(Company.SQL_COLUMN_NAME_TAXNUMBER));
                company.fullName = cursor.getString(cursor.getColumnIndex(Company.SQL_COLUMN_NAME_FULLNAME));
                company.shortName = cursor.getString(cursor.getColumnIndex(Company.SQL_COLUMN_NAME_SHORTNAME));
                company.idNumber = cursor.getString(cursor.getColumnIndex(Company.SQL_COLUMN_NAME_IDNUMBER));
                company.addressPostNumber = cursor.getString(cursor.getColumnIndex(Company.SQL_COLUMN_NAME_ADDRESSPOSTNUMBER));
                company.addressHouseNumber = cursor.getString(cursor.getColumnIndex(Company.SQL_COLUMN_NAME_ADDRESSHOUSENUMBER));
                company.addressMunicipality = cursor.getString(cursor.getColumnIndex(Company.SQL_COLUMN_NAME_ADDRESSMUNICIPALITY));
                company.addressPost = cursor.getString(cursor.getColumnIndex(Company.SQL_COLUMN_NAME_ADDRESSPOST));
                company.addressStreet = cursor.getString(cursor.getColumnIndex(Company.SQL_COLUMN_NAME_ADDRESSSTREET));
                company.searchColumn = cursor.getString(cursor.getColumnIndex(Company.SQL_COLUMN_NAME_SEARCHCOLUMN));
                companies.add(company);
                Log.d("DB READ", company.getFullName());
            } while (cursor.moveToNext());
            cursor.close();
        }
        if (companies != null && companies.size() > 0) {
            Log.d("DB READ", companies.size() + " companies");
            onSuccess(companies, true);
        } else {
            onError("No data read from DB");
            Log.d("DB READ", "NO DATA");
        }
    }
}
