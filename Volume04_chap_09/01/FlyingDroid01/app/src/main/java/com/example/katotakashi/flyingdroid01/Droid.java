package com.example.katotakashi.flyingdroid01;

import android.content.Context;
import android.graphics.Canvas;

/**
 * Created by KATOtakashi on 2015/04/14.
 */
public class Droid extends AbstractGameObject{
    private int defaultX;
    private int defaultY;
    private static final float DefaultVelocity = 2;
    private float velocity = DefaultVelocity;

    public Droid(Context context, int width, int height) {
        super(context, R.drawable.andou_diag01, width, height);
    }

    @Override
    public void setMovingBoundary(int left, int top, int right, int bottom) {
        super.setMovingBoundary(left, top, right, bottom);
        this.bottom -= height;
    }

    public void uplift(boolean on){
        if(on){
            velocity = -DefaultVelocity;
        }else{
            velocity = DefaultVelocity;
        }
    }

    public void setInitialPosition(int x, int y){
        defaultX = x;
        defaultY = y;
        this.x = defaultX;
        this.y = defaultY;
    }

    public void draw(Canvas c){
        draw(c, defaultX, y);
        y += velocity;
        if(y < top){
            y = top;
        }else if(y > bottom){
            y = bottom;
        }
    }
}
