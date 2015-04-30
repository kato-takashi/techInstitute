package com.example.katotakashi.tweenanimation;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScaleAnimation scale = new ScaleAnimation(1, 2, 1, 2, 50, 50);
        // 1000ms間
        scale.setDuration(1000);
        // 1回繰り返す
        scale.setInterpolator(new CycleInterpolator(1));
        AnimationSet set = new AnimationSet(true);



        ImageView img = (ImageView) findViewById(R.id.imageView1); //Tweenアニメーションの適用
        Animation animation = AnimationUtils.
                loadAnimation(this, R.animator.tween_anim);

        set.addAnimation(scale);
        set.addAnimation(animation);
        img.startAnimation(set);
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
