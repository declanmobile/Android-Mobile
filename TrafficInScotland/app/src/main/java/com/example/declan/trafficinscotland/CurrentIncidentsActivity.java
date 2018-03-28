package com.example.declan.trafficinscotland;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.FileNotFoundException;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
/**
 Declan Gibb - S1345890.
 */

public class CurrentIncidentsActivity extends AppCompatActivity {
    public static final String Geo_Position = "GeoPos";
    public static final String Marker_Name = "MarkerName";
    private CurrentIncidentAdapter mAdapter;
    private ListView currentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_incident);

        currentList = (ListView) findViewById(R.id.currentList);



        currentList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                String geopos = mAdapter.getItem(pos).getGeopoint();

                Intent intent = new Intent(CurrentIncidentsActivity.this, MapActivity.class);
                intent.putExtra(Geo_Position, geopos);
                intent.putExtra(Marker_Name, "Current Incident");
                startActivity(intent);
            }
        });


        if (isNetworkAvailable()) {
            CurrentDownloadTask download = new CurrentDownloadTask();
            download.execute();
        } else {
            mAdapter = new CurrentIncidentAdapter(getApplicationContext(), -1, CurrentXmlPullParser.getCurrentIncidentsFromFile(CurrentIncidentsActivity.this));
            currentList.setAdapter(mAdapter);


        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private class CurrentDownloadTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                Downloader.DownloadFromURL("https://trafficscotland.org/rss/feeds/currentincidents.aspx", openFileOutput("CurrentIncident.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mAdapter = new CurrentIncidentAdapter(CurrentIncidentsActivity.this, -1, CurrentXmlPullParser.getCurrentIncidentsFromFile(CurrentIncidentsActivity.this));
            currentList.setAdapter(mAdapter);
        }
    }
}