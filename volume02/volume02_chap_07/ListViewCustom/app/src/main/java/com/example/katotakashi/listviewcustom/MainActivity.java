package com.example.katotakashi.listviewcustom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //画像リソース
            Bitmap image;
            image = BitmapFactory.decodeResource(getResources(), R.drawable.test01);

            //データの作成
            List<ItemData> items = new ArrayList<ItemData>();
            ItemData item1 = new ItemData();
            item1.setImage(image);
            item1.setText("りんご");

            ItemData item2 = new ItemData();
            item2.setImage(image);
            item2.setText("みかん");

            ItemData item3 = new ItemData();
            item3.setImage(image);
            item3.setText("梨");

            ItemData item4 = new ItemData();
            item4.setImage(image);
            item4.setText("ドリアン");

            items.add(item1);
            items.add(item2);
            items.add(item3);
            items.add(item4);

            //CustomAdapterにデータのリストをセット
            CustomAdapter customAdapter = new CustomAdapter(getActivity(), 0, items);

            ListView listView = (ListView)rootView.findViewById(R.id.listView1);
            listView.setAdapter(customAdapter);
            return rootView;
        }
    }
}
