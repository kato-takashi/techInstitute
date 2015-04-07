package com.example.katotakashi.fragmenttablet;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by KATOtakashi on 2015/04/07.
 */
public class DetailFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        if(container==null){
            return null;
        }
        TextView rootView = new TextView(getActivity());
        rootView.setTextSize(18);
        rootView.setText(getArguments().getString("item")+"が選択されました");
        return rootView;
    }
}
