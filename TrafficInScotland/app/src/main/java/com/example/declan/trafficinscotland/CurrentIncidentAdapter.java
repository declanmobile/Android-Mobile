package com.example.declan.trafficinscotland;

import java.util.List;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 Declan Gibb - S1345890.
 */

public class CurrentIncidentAdapter extends ArrayAdapter<CurrentIncident> {

    public CurrentIncidentAdapter(Context ctx, int textViewResourceId, List<CurrentIncident> current) {
        super(ctx, textViewResourceId, current);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        RelativeLayout row = (RelativeLayout)convertView;

        if (null == row) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = (RelativeLayout)inflater.inflate(R.layout.row_current, null);
        }

        TextView titleTxt = (TextView) row.findViewById(R.id.titleTxt);
        TextView descriptionTxt = (TextView) row.findViewById(R.id.descriptionTxt);
        TextView pubdateText = (TextView) row.findViewById(R.id.pubdateText);

        titleTxt.setText(getItem(pos).getTitle());
        titleTxt.setTextColor(Color.BLACK);
        descriptionTxt.setText(getItem(pos).getDescription());
        pubdateText.setText(getItem(pos).getpubDate());
        pubdateText.setTextColor(Color.BLUE);

        return row;
    }
}
