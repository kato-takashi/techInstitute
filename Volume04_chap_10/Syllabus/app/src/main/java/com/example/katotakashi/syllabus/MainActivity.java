package com.example.katotakashi.syllabus;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;


public class MainActivity extends Activity {
    private class CourseItem{
        String date;
        String title;
        String teacher;
        String detail;
    }

    private List<CourseItem> itemList;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void setCourseData(){
        CourseItem item = new CourseItem();
        item.date = "8/28";
        item.title = "ユーティリティによる実践（1）";
        item.teacher = "高橋憲一";
        item.detail = "今度の抗議では一つのアプリとして仕上げることを目指します。";
        itemList.add(item);

        item = new CourseItem();
        item.date = "9/2";
        item.title = "ユーティリティによる実践（2）";
        item.teacher = "高橋憲一";
        item.detail = "2回目とも今度の抗議では一つのアプリとして仕上げることを目指します。";
        itemList.add(item);
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
