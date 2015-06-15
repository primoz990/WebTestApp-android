package com.example.webtestapp.data;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.weblib.data.Company;
import com.example.webtestapp.R;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {

    ListView lvData;
    DataAdapter adapter;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        lvData = (ListView) findViewById(R.id.lvData);

        pd = ProgressDialog.show(this, null, "Loading...");
        new DataHelper(this) {
            @Override
            public void onSuccess(ArrayList<Company> companies, boolean cached) {
                adapter = new DataAdapter(DataActivity.this, R.layout.data_list_item, companies);
                lvData.setAdapter(adapter);
                Log.e("SUCCESS", "Companies: " + companies.size() + " Cached:" + cached);
                if (pd != null && pd.isShowing()) {
                    pd.dismiss();
                }
            }

            @Override
            public void onError(String message) {
                Log.e("ERROR", message);
                if (pd != null && pd.isShowing()) {
                    pd.dismiss();
                }
            }

        }.execute();
    }
}
