package com.example.declan.trafficinscotland;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;

/**
 Declan Gibb - S1345890.
 */

public class CurrentXmlPullParser {

    static final String KEY_ITEM = "ITEM"; // item
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_GEORSS = "georss:point"; // georss:point
    static final String KEY_PUBDATE = "pubdate";


    public static List<CurrentIncident> getCurrentIncidentsFromFile(Context ctx) {

        List<CurrentIncident> currentIncidents;
        currentIncidents = new ArrayList<CurrentIncident>();

        CurrentIncident curCurrentIncident = null;
        String curText = "";
        Boolean ItemFound = false;


        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            FileInputStream fis = ctx.openFileInput("CurrentIncident.xml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            xpp.setInput(reader);

            int eventType = xpp.getEventType();

//            curCurrentIncident = new CurrentIncident();
//            currentIncidents.add(curCurrentIncident);
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_ITEM)) {
                            curCurrentIncident = new CurrentIncident();
                            ItemFound = true;
                        }
                        break;


                    case XmlPullParser.TEXT:
                        curText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_ITEM)) {
                            currentIncidents.add(curCurrentIncident);
                            ItemFound = false;
                        } else if (tagname.equalsIgnoreCase(KEY_TITLE) && ItemFound) {
                            curCurrentIncident.setTitle(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_DESCRIPTION) && ItemFound) {
                            curCurrentIncident.setDescription(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_GEORSS) && ItemFound) {
                            curCurrentIncident.setGeopoint(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_PUBDATE) && ItemFound){
                            curCurrentIncident.setpubDate(curText);
                        }
                        break;

                    default:
                        break;
                }
                eventType = xpp.next();

            }

        } catch(Exception e)
        {
            e.printStackTrace();
        }

                return currentIncidents;
    }


}
