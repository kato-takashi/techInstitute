package com.example.katotakashi.threadtest;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.logging.LogRecord;


public class MainActivity extends Activity implements Runnable {

    private Thread mThread;
    public Handler mHandler;
    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView1);
        mHandler = new Handler() { //メッセージ受信
            public void handleMessage(android.os.Message message) { //メッセージの表示
                String text = (String) message.obj;
                mTextView.setText(text);
                //メッセージの種類に応じてswitch文で制御すれば //イベント制御に利用可能
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        mThread = new Thread(this);
        if (mThread != null) {
            mThread.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mThread = null;
    }

    @Override
    public void run() {
        long time = System.currentTimeMillis();
        long count = 0;
        while (mThread != null) {
            long now = System.currentTimeMillis();
            if (now - time > 1000) {
                Message msg = mHandler.obtainMessage();
                msg.obj = new String("ループが" + count + "回終了しました");
//                mHandler.post(new Runnable(){
//
//                })
//                ハンドラへメッセージの送信
                mHandler.sendMessage(msg);
//                スレッド乗りよう変数を初期化
                time = now;
                count++;
            }
        }
    }
}
