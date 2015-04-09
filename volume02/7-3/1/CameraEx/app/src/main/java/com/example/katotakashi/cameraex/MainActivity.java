package com.example.katotakashi.cameraex;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    Button button1;
    ImageView imageView1;
    public static final int REQUEST_CAMERA = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        imageView1 = (ImageView) findViewById(R.id.imageView1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                カメラでの撮影画面を暗黙のIntentで呼び出し
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                imageUri = insertPhotoUri();

//                    URIをmediastore.EXTRA_OUTPUTにセット
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                結果を受け取るためにstartActivityForResult(intent, requestCode)を使う
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {
            imageView1.setImageURI(imageUri);
        }
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

    private Uri insertPhotoUri() {
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String title = dateFormat.format(new Date(currentTimeMillis));
        String fileName = "IMG_" + title + ".jpg";
        String path = buildPhotoDir() + "/" + fileName;
        File file = new File(path);
// コンテントプロバイダーへのセット
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, title);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.DATA, path);
        values.put(MediaStore.Images.Media.DATE_TAKEN, currentTimeMillis);
        if (file.exists()) {
            values.put(MediaStore.Images.Media.SIZE, file.length());
        }
        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        return uri;
    }
}
