package com.example.katotakashi.flyingdroid01;

import android.content.Context;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by KATOtakashi on 2015/04/20.
 */
public class Enemy extends AbstractGameObject{
    static Random random;

    static {
        random = new Random(System.currentTimeMillis());
    }
    public Enemy(Context context, int width, int height) {
        super(context,R.drawable.enemy_pinkdude_jump, width, height);
    }

    @Override
    public void setMovingBoundary(int left, int top, int right, int bottom) {
        super.setMovingBoundary(left, top, right, bottom);
        left -= width;
        x = right;
        this.bottom -= height;
    }

    private int getY(){
        return random.nextInt(this.bottom);
    }
    public void draw(Canvas c){
        draw(c, x, y);
        x -= 5;
        if(x < left){
            x = right;
            y = getY();
        }
    }
}
