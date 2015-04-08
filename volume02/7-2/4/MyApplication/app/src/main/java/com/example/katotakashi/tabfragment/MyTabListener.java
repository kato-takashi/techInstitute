package com.example.katotakashi.tabfragment;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Created by KATOtakashi on 2015/04/08.
 */
public class MyTabListener implements ActionBar.TabListener {
    private Fragment mFragment;

//    新規タブを作成する際にフラグメントインスタンスを一緒に渡す
    public MyTabListener(Fragment fragment){
        mFragment = fragment;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft){
//        タブが選択された時の処理
//        Fragmentを追加する
        ft.add(R.id.fragment_content, mFragment, null);
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft){
//        タブが切り替えられて非選択になった時の処理
//        Fragmentを削除する
        ft.remove(mFragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft){
//        同じタブを再度タップされた時の処理
//                ここでは何も記述しない

    }
}
