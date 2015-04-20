package com.example.katotakashi.flyingdroid01;

import android.content.Context;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


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
        gameThread.setViewSize(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        gameThread = null;
    }

    class GameThread extends Thread {
        SurfaceHolder surfaceHolder;
        boolean shouldContinue = true;
        int width;
        int height;

        Droid droid;
        static final int droidSize = 200;

        Enemy enemy;
        long frameNo = 0;
        long nextGenFrame = 100;
        Context context;
        static final int EnemyNum = 5;
        Enemy[] enemys;

        static final int enemySize = 200;

        SoundPool sound;
        int hitSoundId;
        int rocketSoundId;
        int rocketStreamId;


        public GameThread(SurfaceHolder surfaceHolder, Context context, Handler handler) {
            this.surfaceHolder = surfaceHolder;
            droid = new Droid(context, droidSize, droidSize);
            droid.setInitialPosition(100, 0);

            this.context = context;
            enemys = new Enemy[EnemyNum];
            enemys[0] = new Enemy(context, enemySize, enemySize);
            setupSoundPool();
        }

        public void setupSoundPool(){
            sound = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
            hitSoundId = sound.load(context, R.raw.quick_explosion, 1);
            rocketStreamId = sound.load(context, R.raw.rockets, 1);
        }

        public void releaseSoundPool(){
            sound.release();
        }

        @Override
        public void run() {
            while (shouldContinue) {
                Canvas c = surfaceHolder.lockCanvas();
                draw(c);
                surfaceHolder.unlockCanvasAndPost(c);
            }
        }

        public void setViewSize(int width, int height) {
            this.width = width;
            this.height = height;

            droid.setMovingBoundary(0, 0, width, height);
            for (int i = 0; i < EnemyNum; i++) {
                if (enemys[i] != null) {
                    enemys[i].setMovingBoundary(0, 0, width, height);
                }
            }
        }

        public void upliftDroid(boolean on) {

            droid.uplift(on);
            if(on){
                rocketStreamId = sound.play(rocketSoundId, 0.5f, 0.5f, 0, -1, 1.0f);
            }else{
                sound.stop(rocketStreamId);
            }
        }

        public void draw(Canvas c) {
            for (int i = 0; i < EnemyNum; i++) {
                if (enemys[i] != null && enemys[i].isHit(droid)) {
                    droid.setImageResourceId(R.drawable.andou_die01);
                    sound.play(hitSoundId, 1.0f, 1.0f, 0, 0, 1.0f);
                }
            }
            c.drawARGB(255, 0, 0, 0);
            droid.draw(c);

            for (int i = 0; i < EnemyNum; i++) {
                if (enemys[i] != null) {
                    enemys[i].draw(c);
                }
            }
            if (frameNo == nextGenFrame) {
                for (int i = 0; i < EnemyNum; i++) {
                    if (enemys[i] == null) {
                        enemys[i] = new Enemy(context, enemySize, enemySize);
                        enemys[i].setMovingBoundary(0, 0, width, height);
                        nextGenFrame += 100;
                        break;
                    }
                }
            }
            frameNo++;
        }
    }

    GameThread gameThread;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //独自の描画スレッドを実装
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        gameThread = new GameThread(holder, context, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        });

        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dispatchEvent(event);
                return dispatchEvent(event);
            }
        });
    }

    private boolean dispatchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                gameThread.upliftDroid(true);
                return true;
            case MotionEvent.ACTION_UP:
                gameThread.upliftDroid(false);
                return false;
            default:
                return false;
        }
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
