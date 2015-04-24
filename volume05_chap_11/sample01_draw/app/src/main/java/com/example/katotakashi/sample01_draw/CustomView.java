package com.example.katotakashi.sample01_draw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * TODO: document your custom view class.
 */
public class CustomView extends View{

//    コンストラクタ
    public CustomView(Context context) {
        super(context);
//        何もしません
    }

    //スタイルを適用する際のコンストラクタ
    public CustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //        何もしません
    }

//    XMLより呼び出す際のコンストラクタ
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        initPaint();
    }

//    描画用のビットマップ、キャンバス、パス、ペイントの設定
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;

    private Button delBtn;

//    描画用のPaintの初期化
    private void initPaint(){
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);

        delBtn = (Button)findViewById(R.id.deleteBtn);
//        delBtn.setOnClickListener(delBtnClickListener);
    }
//    マウスイベント
    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        画面サイズ変更時に通知
        Log.v("View", "onSizeChanged Width: "+ w + ", Height: " + h);
//        Canvasを作成
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Viewの描画関数でパスを描画する
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                break;
        }
        invalidate();//Viewの再描画
        return true;
    }

    private void touch_start(float x, float y){
//        タッチ開始
        Log.v("View", "touch_start");
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }


    private void touch_move(float x, float y){
//        指が移動している間の処理
        Log.d("View", "touch_move");
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y -mY);
//        しきい値より移動量が多ければ線をつなぐ
        if(dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE){
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }

    private void touch_up(){
//        タッチ終了
        Log.v("View", "touch_up");
//        線を描く
        mPath.lineTo(mX, mY);
        mCanvas.drawPath(mPath, mPaint);
        mPath.reset();
    }

    OnClickListener delBtnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.v("click", "del");

        }
    };

}
