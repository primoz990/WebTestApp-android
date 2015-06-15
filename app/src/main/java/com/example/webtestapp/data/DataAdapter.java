package com.example.webtestapp.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.weblib.data.Company;
import com.example.webtestapp.R;

import java.util.ArrayList;

public class DataAdapter extends ArrayAdapter<Company> {

    Context context;
    int layoutResourceId;
    ArrayList<Company> data = null;

    public DataAdapter(Context context, int layoutResourceId, ArrayList<Company> data) {
        super(context, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DataDataHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutResourceId, null);
            holder = new DataDataHolder();

            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvCompanyListItemTitle);
            holder.tvDetail = (TextView) convertView.findViewById(R.id.tvCompanyListItemDetail);

            convertView.setTag(holder);
        } else {
            holder = (DataDataHolder) convertView.getTag();
        }

        final Company item = data.get(position);

        holder.tvTitle.setText(item.fullName);
        holder.tvDetail.setText(item.addressStreet + " "
                + item.addressHouseNumber + " "
                + item.addressMunicipality);

        return convertView;
    }

}
