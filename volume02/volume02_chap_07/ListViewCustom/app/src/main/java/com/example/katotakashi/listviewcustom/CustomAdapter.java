package com.example.katotakashi.listviewcustom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by KATOtakashi on 2015/04/06.
 */
public class CustomAdapter extends ArrayAdapter<ItemData> {
    //アイテムのレイアウト取得用
    private LayoutInflater layoutInfater_;
    public CustomAdapter(Context context, int textViewResourceId, List<ItemData> objects){
        super(context, textViewResourceId, objects);
        layoutInfater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //表示する行(position)のデータを得る
        ItemData item = (ItemData)getItem(position);

        //convertViewは使いまわしされている可能性があるので、nullの時だけ楽しく作る
        if(null == convertView){
            convertView = layoutInfater_.inflate(R.layout.item_layout, null);
        }

        //ItemDataのデータを各Viewにセットする
        ImageView imageView;
        imageView = (ImageView)convertView.findViewById(R.id.image);
        imageView.setImageBitmap(item.getImage());

        TextView textView;
        textView = (TextView)convertView.findViewById(R.id.text);
        textView.setText(item.getText());

        return convertView;
    }
}
