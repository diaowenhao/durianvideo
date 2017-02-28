package qf.com.durianvideo.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import qf.com.durianvideo.activity.SpecialActivity;
import qf.com.durianvideo.adapter.ImgAdapter;
import qf.com.durianvideo.adapter.MyAdapterofHomePage;
import qf.com.durianvideo.util.MyhttpUtils;


/**
 * 2.28改——原来思路太傻比，改成recyclerview嵌套recyclerview
 *
 * 那么每个item配套的都是一个json数组，先以string的形式传进去
 *
 **/
public class HomePageFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private Context mContext;
    private MyAdapterofHomePage mMyAdapterofHomePage;
    private View header;
    private ArrayList<String> datas=new ArrayList<String>();

    private ViewPager mViewPager;
    private List<Integer> pagerList = new ArrayList<Integer>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        mContext = getActivity();

        //装数据——也就是把json数据下载之后，装载到list中，去适配器里面解析
        //initData();
        mRecyclerView= (RecyclerView)view.findViewById(R.id.mRecyclerView);
        //设置成列表显示的
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        //设置适配器
        mMyAdapterofHomePage = new MyAdapterofHomePage(mContext);
        mRecyclerView.setAdapter(mMyAdapterofHomePage);
        mMyAdapterofHomePage.addDatas(datas);
        //设置头部布局
        setHeader(mRecyclerView);


        return view;
    }


    private void initData() {
        for (int i=0;i<20;i++){
            datas.add("中国"+i);
        }
    }

    public void setHeader(RecyclerView view) {
        header = LayoutInflater.from(mContext).inflate(R.layout.home_header_item, view, false);
        mViewPager = (ViewPager) header.findViewById(R.id.home_viewpager);
        //在这里搞头部布局
        initPager();
        mViewPager.setAdapter(new ImgAdapter(pagerList, mContext));
        //设置viewpager第一个默认展示的页面
        mViewPager.setCurrentItem(Integer.MAX_VALUE/2);

        mMyAdapterofHomePage.setHeaderView(header);
    }

    //测试图片，待联网
    private void initPager(){
        pagerList.add(R.drawable.img012);
   		pagerList.add(R.drawable.img017);
        pagerList.add(R.drawable.img021);
        pagerList.add(R.drawable.img030);

    }

    //四个图标的点击效果——bug
    public void onHeadTurn(View view){
        switch (view.getId()){
            case R.id.special:
                Intent mIntent = new Intent(mContext, SpecialActivity.class);
                startActivity(mIntent);
                break;
            case R.id.chapter:
                break;
            case R.id.gift:
                break;
            case R.id.money:
                break;
        }
    }

}
