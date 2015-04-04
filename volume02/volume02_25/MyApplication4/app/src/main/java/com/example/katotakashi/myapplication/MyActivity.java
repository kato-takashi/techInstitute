package com.example.katotakashi.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by katoTakashi on 2015/04/04.
 */
public class MyActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView v = new MyView(this);
        Intent intent = getIntent();
        int vx = intent.getIntExtra("vx", 1);
        int vy = intent.getIntExtra("vy", 1);
        v.mVX = vx;
        v.mVY = vy;
        setContentView(v);

    }
}
