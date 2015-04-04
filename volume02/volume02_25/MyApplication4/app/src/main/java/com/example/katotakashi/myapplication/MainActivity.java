package com.example.katotakashi.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.net.URI;


public class MainActivity extends Activity {
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG", "onCreateが実行されました");

//        MyView v = new MyView(this);
//        setContentView(v);

        Button btn = (Button) findViewById(R.id.button1);

        btn.setText("Hello");
        mPlayer = MediaPlayer.create(this, R.raw.piano);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Button b = (Button) arg0;
                b.setText("こんにちわ");
                mPlayer.start();
            }
        });

        Button btn2 = (Button) findViewById(R.id.btn2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //地図
//                Uri uri = Uri.parse("geo:0,0?q=Shibuya");
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
                //電話
//                Uri uri = Uri.parse("tel:090-6174-3014");
//                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
//                startActivity(intent);
                //明示的なintent
                Intent intent = new Intent(MainActivity.this, MyActivity.class);
                intent.putExtra("vx", 10);
                intent.putExtra("vy", 10);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
