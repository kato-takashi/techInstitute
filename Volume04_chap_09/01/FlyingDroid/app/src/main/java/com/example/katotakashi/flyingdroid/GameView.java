package com.example.katotakashi.flyingdroid;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * Created by KATOtakashi on 2015/04/14.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        gameThread = null;
    }

    class GameThread extends Thread {
        SurfaceHolder surfaceHolder;
        boolean shouldContinue = true;

        public GameThread(SurfaceHolder surfaceHolder, Context context, Handler handler){
            this.surfaceHolder = surfaceHolder;
        }

        @Override
        public void run(){
            while (shouldContinue){
                Canvas c = surfaceHolder.lockCanvas();
                draw(c);
                surfaceHolder.unlockCanvasAndPost(c);
            }
        }

        public void draw(Canvas c) {
            c.drawARGB(255, 0, 0, 0);
        }
    }

    GameThread gameThread;

    public GameView(Context context){
        super(context);
    }

    public GameView(Context context, AttributeSet attrs){
        super(context, attrs);
        //独自の描画スレッドを実装
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        gameThread = new GameThread(holder, context, new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        });
    }

    public GameView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }
}
