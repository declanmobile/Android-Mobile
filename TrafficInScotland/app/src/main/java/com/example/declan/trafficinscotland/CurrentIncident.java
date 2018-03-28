package com.example.declan.trafficinscotland;

import java.text.DateFormat;

/**
        Declan Gibb - S1345890.
 */

public class CurrentIncident {
    private String title;
    private String description;
    private String geopoint;
    private String pubDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getGeopoint()
    {
        return geopoint;

    }

    public void setGeopoint(String geopoint)
    {
        this.geopoint = geopoint;
    }

    public String getpubDate()
    {
     return pubDate;
    }

    public  void setpubDate(String pubDate)
    {
        this.pubDate = pubDate;
    }


    @Override
    public String toString() {
        return "Incident [title=" + title + ", description=" + description + ", pubDate="
                + pubDate + ", geopoint=" +  geopoint + "]";

    }

}


