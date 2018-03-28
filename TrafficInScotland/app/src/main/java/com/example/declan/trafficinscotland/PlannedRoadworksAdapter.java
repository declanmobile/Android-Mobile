package com.example.declan.trafficinscotland;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 Declan Gibb - S1345890.
 */

public class PlannedRoadworksAdapter extends ArrayAdapter<PlannedRoadworks> {

    public PlannedRoadworksAdapter(Context ctx, int textViewResourceId, List<PlannedRoadworks> current) {
    super(ctx, textViewResourceId, current);
}

    public int RAGColor = Color.BLUE;
    public String DescNoDates = "", StartEndStr = "";

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        String StartDate = "", EndDate = "", DescWithDates = "";


        RelativeLayout row = (RelativeLayout)convertView;

        if (null == row) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = (RelativeLayout)inflater.inflate(R.layout.row_current2, null);

        }

        TextView titleText = (TextView) row.findViewById(R.id.titleText2);
        TextView descriptionText = (TextView) row.findViewById(R.id.descriptionText2);
        TextView pubdateText = (TextView) row.findViewById(R.id.pubdateText2);

        DescWithDates = getItem(pos).getDescription();

        extractdates(DescWithDates);

        titleText.setText(getItem(pos).getTitle());
        titleText.setTextColor(Color.BLACK);
        descriptionText.setText(DescNoDates);
        pubdateText.setText(StartEndStr);
        pubdateText.setTextColor(RAGColor);




        return row;
    }
    void extractdates(String DescWithDates)
    {
        int StartDateStartPos = DescWithDates.indexOf("Start Date:");
        int StartDateEndPos = DescWithDates.indexOf("<br",StartDateStartPos);
        int EndEndStartPos = DescWithDates.indexOf("End Date:");
        int EndDateEndPos = DescWithDates.indexOf("<br",EndEndStartPos);
        int PlannedDays = 0;
        Date StartDt = null, EndDt = null;

        String StartDatestr = DescWithDates.substring(StartDateStartPos+12,StartDateEndPos-8);  // +12 to omit the text "Start Date: "
        String EndDatestr = DescWithDates.substring(EndEndStartPos+10,EndDateEndPos-8);         // +10 to omit the text "End Date: "

        StartDt = StrToDate(StartDatestr);
        EndDt = StrToDate(EndDatestr);

        // Calculate the number of days between the planned start and planned end
        PlannedDays = DaysBetween(StartDt, EndDt);

        // set colour to Green, Amber, Red based on number of days planned works
        if (PlannedDays <=7)
        {
            RAGColor = Color.parseColor("#009900"); // Green
        } else if (PlannedDays <= 30)
        {
            RAGColor = Color.parseColor("#ff9900"); // Amber
        } else
        {
            RAGColor = Color.RED;
        }

        DescNoDates = DescWithDates.substring(EndDateEndPos+6);  // +6 to omit the end tag
        StartEndStr = StartDatestr + " - \n" + EndDatestr;

    }

    // Expect InStr to be in Format "Day, dd MMMM yyyy" e.g. Friday, 30 March 2018
    Date StrToDate(String InStr)
    {
        DateFormat dateFormat = new SimpleDateFormat("E, dd MMMMM yyyy");
        Date OutDt = null;
        try {
            OutDt = dateFormat.parse(InStr);
        } catch (java.text.ParseException e){
            Log.d("Error", "" + e);
        }
        System.out.println(OutDt);

        return OutDt;
    }

    int DaysBetween(Date FromDt, Date ToDt)
    {
        long difference = ToDt.getTime() - FromDt.getTime();
        int DaysDiff = (int)(difference / (1000*60*60*24));
        return DaysDiff;
    }
}