package com.example.katotakashi.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by KATOtakashi on 2015/04/03.
 */
public class MyView extends View {
    int mX = 100;
    int mY = 100;
    int mVX = 1;
    int mVY = 1;

    public MyView(Context context){
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawColor(Color.argb(255, 80, 116, 62));
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawCircle(mX, mY, 100, paint);
        if(mX > this.getWidth()){
            mVX  = -mVX;
        }else if (mX < 0){
            mVX = -mVX;
        }
        if(mY > this.getHeight()){
            mVY  = -mVY;
        }else if (mX < 0){
            mVY = -mVY;
        }

        mX += mVX;
        mY += mVY;
        invalidate();
    }
}
