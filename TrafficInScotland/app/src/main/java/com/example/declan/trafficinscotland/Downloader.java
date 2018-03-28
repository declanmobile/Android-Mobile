package com.example.declan.trafficinscotland;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 Declan Gibb - S1345890.
 */

public class Downloader {


    public static void DownloadFromURL(String URL, FileOutputStream fos) {
        try {
            URL url = new URL(URL);

            URLConnection ucon = url.openConnection();

            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            byte data[] = new byte[1024];

            int count;

            while ((count = bis.read(data)) != -1) {
                bos.write(data, 0, count);
            }

            bos.flush();
            bos.close();

        }
        catch (IOException e){
            Log.d("Error", "" + e);
        }

    }
}