package com.example.katotakashi.camerapreview;

import android.content.Context;
import android.hardware.Camera;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;


/**
 * Created by KATOtakashi on 2015/04/10.
 */
public class CameraView extends SurfaceView implements SurfaceHolder.Callback,Camera.PictureCallback {
    private SurfaceHolder holder;
    private Camera camera;
    private MainActivity activity;

    public CameraView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        activity = (MainActivity)context;
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try{
            //カメラをオープン
            camera = Camera.open();
            //プレビューディスプレイ（表示先）を設定
            camera.setPreviewDisplay(holder);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //画面に変化があった時はstartPreview
        camera.startPreview();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
            //プレビューを停止
        camera.stopPreview();
        //カメラをリリース
        camera.release();
        camera = null;
    }



    public void onPictureTaken(byte[] date, Camera camera){
        try {
            activity.insertPhoto(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        camera.startPreview();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        //タッチイベントで
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            camera.takePicture(null, null, null, this);
        }
        return true;
    }


}
