package com.example.katotakashi.listviewcustom;

import android.graphics.Bitmap;

/**
 * Created by KATOtakashi on 2015/04/06.
 */
public class ItemData {
    private Bitmap _image;
    private String _text;

    public void set_image(Bitmap image){
        _image = image;
    }

    public void setImage(Bitmap image){
        _image = image;
    }

    public Bitmap getImage(){
        return _image;
    }

    public void setText(String text){
        _text = text;
    }

    public String getText(){
        return _text;
    }
}
