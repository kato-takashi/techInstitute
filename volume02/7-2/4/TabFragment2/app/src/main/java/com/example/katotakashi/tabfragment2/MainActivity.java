package com.example.katotakashi.tabfragment2;

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
        // ActionBarを取得してモードをタブモードへセット
        final android.app.ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_TABS);
        // タイトルを非表示
        actionBar.setDisplayShowTitleEnabled(false);
        // Fragmentを生成してTabへセット
        android.app.Fragment fragment1 = new TabFragment1();
        // 新しく生成したTabインスタンスにタイトル文字列、アイコン、リスナーをセット
        android.app.ActionBar.Tab tab1 = actionBar.newTab();
        tab1.setText("Tab1");
        tab1.setIcon(R.drawable.ic_launcher);
        tab1.setTabListener(new MyTabListener(fragment1)); actionBar.addTab(tab1);

        // Tab2も同様に作成
        android.app.Fragment fragment2 = new TabFragment2();
        android.app.ActionBar.Tab tab2 = actionBar.newTab();
        tab2.setText("Tab2");
        tab2.setIcon(R.drawable.ic_launcher);
        tab2.setTabListener(new MyTabListener(fragment2));
        actionBar.addTab(tab2);
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
            return rootView;
        }
    }
}
