package qf.com.durianvideo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.headerandfooterwrapper_library.HeaderAndFooterWrapper;
import com.example.refreshrecyclerview_library.RefreshRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import qf.com.durianvideo.R;
import qf.com.durianvideo.adapter.RefreshRecyclerViewAdapter;

import qf.com.durianvideo.util.DividerItemDecoration;
import qf.com.durianvideo.util.MyhttpUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserOfSearchFragment extends Fragment {

    private static final int REFRESH = 0;
    private static final int LOADMORE = 1;
    private HeaderAndFooterWrapper headerAndFooterWrapper;
    private RefreshRecyclerViewAdapter recyclerAdapter;
    private RefreshRecyclerView search_user_recyclerview;
    private   Context  context;
    private   int  page=1;
    private String msg="";
    private  String  userpath="http://www.liulianvideo.com:8088/filmDataSys/movieList/queryUserListByKeyWord.html?pageNum="+page+"&keyWord=";

    private List<JSONObject>  datalist=new ArrayList<>();

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what) {
                case REFRESH:
                    search_user_recyclerview.onFinishRefresh(true);
                    break;
                case LOADMORE:
                    search_user_recyclerview.onFinishRefresh(false);
                    break;
            }
        }
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_of_search, container, false);
        search_user_recyclerview = (RefreshRecyclerView)view.findViewById(R.id.search_user_recyclerview);


        initRefreshRecyclerView();
        //获取到宿主activity传递过来的数据
        Bundle bundle = getArguments();
        if (bundle != null) {
             msg = bundle.getString("msg");
                 initdata(userpath+msg);
        }

        return view;
    }

    private void initdata(String url) {
        new MyhttpUtils(context, new MyhttpUtils.GetStringBack() {
            @Override
            public void getString(String str) {

                try {
                    JSONObject jsonObject=new JSONObject(str);
                    //下载数据到list中
                    if (jsonObject.getString("success").equals("true")){
                        JSONArray jsonArray=jsonObject.getJSONArray("userLists");
                            int j=jsonArray.length();
                        Log.d("====",jsonArray.toString());
                        for (int i=0;i<j;i++){

                         datalist.add(jsonArray.getJSONObject(i));

                        }
                        recyclerAdapter.notifyDataSetChanged();
                        headerAndFooterWrapper.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).download(url);


    }

    private void initRefreshRecyclerView() {
        search_user_recyclerview.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        recyclerAdapter = new RefreshRecyclerViewAdapter(context,datalist);
        Log.d("===---=",datalist.toString());
        headerAndFooterWrapper = new HeaderAndFooterWrapper(recyclerAdapter);
//        设置布局管理器
        search_user_recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        search_user_recyclerview.setAdapter(headerAndFooterWrapper);
        search_user_recyclerview.addHeaderView(search_user_recyclerview.getHeaderView(), headerAndFooterWrapper);
        search_user_recyclerview.addFooterView(search_user_recyclerview.getFooterView(), headerAndFooterWrapper);
        search_user_recyclerview.setOnRefreshListener(new OnRecyclerRefreshListener());
    }

    public class OnRecyclerRefreshListener implements RefreshRecyclerView.OnRefreshListener {
        @Override
        public void onPullDownRefresh() {
//           执行下拉刷新操作，一般是联网更新数据
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    handler.sendEmptyMessage(REFRESH);
                }
            }).start();

            initdata(userpath+page);
            recyclerAdapter.notifyDataSetChanged();
            headerAndFooterWrapper.notifyDataSetChanged();
        }

        @Override
        public void onLoadingMore() {
//            执行上拉加载操作，一般是联网请求更多分页数据
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    handler.sendEmptyMessage(LOADMORE);
                }
            }).start();
            page+=1;
            initdata(userpath+page);
            recyclerAdapter.notifyDataSetChanged();
            headerAndFooterWrapper.notifyDataSetChanged();
        }
    }


}
