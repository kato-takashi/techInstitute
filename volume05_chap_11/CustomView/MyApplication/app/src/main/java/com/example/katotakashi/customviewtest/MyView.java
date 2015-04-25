package com.example.katotakashi.customviewtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * TODO: document your custom view class.
 */
public class MyView extends LinearLayout {
    /** 通常のコンストラクタ .*/
    public MyView(Context context) {
        super(context);
    }

    /** xml配置用のコンストラクタ .*/
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        /** カスタムビューのxmlからのlayoutの情報を読み込む. */
        View layout = LayoutInflater.from(context).inflate(
                R.layout.sample_my_view, this);

        /** カスタムビューのボタンに任意の文字を表示する例. */
        Button btn1 = (Button) layout.findViewById(R.id.btn1);
        btn1.setText("カスタムビューのボタン1");

        /** カスタムビューのボタンに任意の文字を表示する例. */
        Button btn2 = (Button) layout.findViewById(R.id.btn2);
        btn2.setText("カスタムビューのボタン2");

        /** カスタムビューのボタンに任意の文字を表示する例. */
        Button btn3 = (Button) layout.findViewById(R.id.btn3);
        btn3.setText("カスタムビューのボタン3");

        btn1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                showToast();
            }
        });
    }

    private void showToast() {
        Toast.makeText(this.getContext(), "Application", Toast.LENGTH_SHORT).show();
    }
}
