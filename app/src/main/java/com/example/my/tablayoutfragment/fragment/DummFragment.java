package com.example.my.tablayoutfragment.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.my.tablayoutfragment.R;


public class DummFragment extends Fragment {
    private String title;
    private int tabIndex;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dummy, container, false);
        textView = (TextView) view.findViewById(R.id.textView_fragment_title);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        title = bundle.getString("title");
        tabIndex = bundle.getInt("tabIndex");
        textView.setText(title + "角标："+tabIndex);
    }

    public static DummFragment newInstance(int tabIndex, String title) {
        Bundle bundle = new Bundle();
        bundle.putInt("tabIndex", tabIndex);
        bundle.putString("title", title);
        DummFragment fragment = new DummFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
