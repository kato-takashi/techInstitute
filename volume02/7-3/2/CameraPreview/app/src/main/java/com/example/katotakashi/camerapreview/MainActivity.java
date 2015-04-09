package com.example.katotakashi.camerapreview;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.MediaStore.Images.ImageColumns.DATE_TAKEN;
import static android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
import static android.provider.MediaStore.MediaColumns.DATA;
import static android.provider.MediaStore.MediaColumns.DISPLAY_NAME;
import static android.provider.MediaStore.MediaColumns.MIME_TYPE;
import static android.provider.MediaStore.MediaColumns.SIZE;
import static android.provider.MediaStore.MediaColumns.TITLE;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CameraView(this));
    }

    public Uri insertPhoto(byte[] data) throws IOException {
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String title = dateFormat.format(new Date(currentTimeMillis));
        String fileName = "IMG" + title + ".jpeg";
        String path = buildPhotoDir() + "/" + fileName;
        File file = new File(path);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(data);
            out.close();
        } catch (Exception e) {
            if (out != null) {
                out.close();
            }
        }

//        コンテントプロバイダーへのセット
        ContentValues values = new ContentValues();
        values.put(TITLE, title);
        values.put(DISPLAY_NAME, fileName);
        values.put(MIME_TYPE, "image/jpeg");
        values.put(DATA, path);
        values.put(DATE_TAKEN, currentTimeMillis);
        if (file.exists()) {
            values.put(SIZE, file.length());
        }
        Uri uri = getContentResolver().insert(EXTERNAL_CONTENT_URI, values);
        return uri;
    }

    private String buildPhotoDir() {
        String dirPath = "";
        File photoDir = null;
        File extStorageDir = Environment.getExternalStorageDirectory();
        if (extStorageDir.canWrite()) {
            photoDir = new File(extStorageDir.getPath() + "/" + getPackageName());
            if (!photoDir.exists()) {
                photoDir.mkdirs();
            }
            if (photoDir.canWrite()) {
                dirPath = photoDir.getPath();
            }
        }
        return dirPath;
    }

}

