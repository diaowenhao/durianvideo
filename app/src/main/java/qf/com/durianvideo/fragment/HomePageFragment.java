package qf.com.durianvideo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import qf.com.durianvideo.R;
import qf.com.durianvideo.adapter.MyAdapterofHomePage;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private Context mContext;

    List<String> datas=new ArrayList<String>();

    public HomePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        mContext = getActivity();

        initData();
        mRecyclerView= (RecyclerView)view.findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2));

        mRecyclerView.setAdapter(new MyAdapterofHomePage(mContext,datas));
        return view;
    }

/*    private void initScreen() {
        DisplayMetrics mDisplayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        ScreenWidth=mDisplayMetrics.widthPixels;
    }*/

    private void initData() {
        for (int i=0;i<20;i++){
            datas.add("中国"+i);
        }
    }
}
