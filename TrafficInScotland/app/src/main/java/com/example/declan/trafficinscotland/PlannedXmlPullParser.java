package com.example.declan.trafficinscotland;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 Declan Gibb - S1345890.
 */

public class PlannedXmlPullParser {

        static final String KEY_ITEM = "ITEM"; // item
        static final String KEY_TITLE = "title";
        static final String KEY_DESCRIPTION = "description";
        static final String KEY_GEORSS = "georss:point"; // georss:point
        static final String KEY_PUBDATE = "pubdate";


public static List<PlannedRoadworks> getPlannedRoadworksFromFile(Context ctx) {

            List<PlannedRoadworks> plannedRoadworkss;
            plannedRoadworkss = new ArrayList<PlannedRoadworks>();

            PlannedRoadworks curPlanedRoadworks = null;
            String curText = "";
            Boolean ItemFound = false;


            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();

                FileInputStream fis = ctx.openFileInput("PlannedRoadworks.xml");
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

                xpp.setInput(reader);

                int eventType = xpp.getEventType();

//            curCurrentIncidents = new CurrentIncident();
//            currentIncidentss.add(curCurrentIncidents);
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String tagname = xpp.getName();

                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if (tagname.equalsIgnoreCase(KEY_ITEM)) {
                                curPlanedRoadworks = new PlannedRoadworks();
                                ItemFound = true;
                            }
                            break;


                        case XmlPullParser.TEXT:
                            curText = xpp.getText();
                            break;

                        case XmlPullParser.END_TAG:
                            if (tagname.equalsIgnoreCase(KEY_ITEM)) {
                                plannedRoadworkss.add(curPlanedRoadworks);
                                ItemFound = false;
                            } else if (tagname.equalsIgnoreCase(KEY_TITLE) && ItemFound) {
                                curPlanedRoadworks.setTitle(curText);
                            } else if (tagname.equalsIgnoreCase(KEY_DESCRIPTION) && ItemFound) {
                                curPlanedRoadworks.setDescription(curText);
                            } else if (tagname.equalsIgnoreCase(KEY_GEORSS) && ItemFound) {
                                curPlanedRoadworks.setGeopoint(curText);
                            } else if (tagname.equalsIgnoreCase(KEY_PUBDATE) && ItemFound){
                                curPlanedRoadworks.setpubDate(curText);
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

            return plannedRoadworkss;
        }
}