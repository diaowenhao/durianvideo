package qf.com.durianvideo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.headerandfooterwrapper_library.HeaderAndFooterWrapper;
import com.example.refreshrecyclerview_library.RefreshRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import qf.com.durianvideo.R;
import qf.com.durianvideo.adapter.MyAdapterOfAttention;
import qf.com.durianvideo.adapter.RefreshRecyclerViewAdapter;
import qf.com.durianvideo.util.DividerItemDecoration;
import qf.com.durianvideo.util.MyhttpUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttentionFragment extends Fragment {

    private static final int REFRESH = 0;
    private static final int LOADMORE = 1;
    private HeaderAndFooterWrapper headerAndFooterWrapper;
    private MyAdapterOfAttention  attentionAdapter;
    private RefreshRecyclerView attentionFragment_rv;
    private   Context  context;
    private   int  page=1;

    private  String  recommendpath="http://www.liulianvideo.com:8088/filmDataSys/movieList/hotRecommendUserList.html?userId=d7c345757ae647a591572191b5b8d7ae";
    private  String   attentionpath="http://www.liulianvideo.com:8088/filmDataSys/movieList/movieListByMySubscription.html?pageNum=";
    private  String  userid="&userId=d7c345757ae647a591572191b5b8d7ae";

    private List<JSONObject> datarecommendlist=new ArrayList<>();
    private List<JSONObject>  dataattentionlist=new ArrayList<>();
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what) {
                case REFRESH:
                    attentionFragment_rv.onFinishRefresh(true);
                    break;
                case LOADMORE:
                    attentionFragment_rv.onFinishRefresh(false);
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

        View  view=inflater.inflate(R.layout.fragment_attention, container, false);

        attentionFragment_rv= (RefreshRecyclerView) view.findViewById(R.id.attentionFragment_rv);
        attentionFragment_rv.setLayoutManager(new LinearLayoutManager(context));
        initRefreshRecyclerView();
        initfist(attentionpath+page+userid);
        getrecommend();
        return view;
    }

   private void  getrecommend(){
       new MyhttpUtils(context, new MyhttpUtils.GetStringBack() {
           @Override
           public void getString(String str) {
               try {
                   JSONObject jsonObject=new JSONObject(str);
                   //下载数据到list中
                   if (jsonObject.getString("success").equals("true")){
                       JSONArray jsonArray=jsonObject.getJSONArray("UpUserList");
                       int j=jsonArray.length();

                       if (j>0){
                           for (int i=0;i<j;i++){
                               datarecommendlist.add(jsonArray.getJSONObject(i));
                           }
                           attentionAdapter.notifyDataSetChanged();
                           headerAndFooterWrapper.notifyDataSetChanged();
                       }
                   }

               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       }).download(recommendpath);

    }

    private  void  initfist(String url){
        new MyhttpUtils(context, new MyhttpUtils.GetStringBack() {
            @Override
            public void getString(String str) {
                try {
                    JSONObject jsonObject=new JSONObject(str);
                    //下载数据到list中
                    if (jsonObject.getString("success").equals("true")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("UpUserList");
                        int j = jsonArray.length();

                        if (j > 0) {
                            for (int i = 0; i < j; i++) {
                                dataattentionlist.add(jsonArray.getJSONObject(i));
                            }
                            attentionAdapter.notifyDataSetChanged();
                            headerAndFooterWrapper.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).download(url);


    }


    private void initRefreshRecyclerView() {
        attentionFragment_rv.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));

        attentionAdapter = new MyAdapterOfAttention(dataattentionlist,context,datarecommendlist);
        headerAndFooterWrapper = new HeaderAndFooterWrapper(attentionAdapter);
//        设置布局管理器
        attentionFragment_rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        attentionFragment_rv.setAdapter(headerAndFooterWrapper);
        attentionFragment_rv.addHeaderView(attentionFragment_rv.getHeaderView(), headerAndFooterWrapper);
        attentionFragment_rv.addFooterView(attentionFragment_rv.getFooterView(), headerAndFooterWrapper);
        attentionFragment_rv.setOnRefreshListener(new  OnRecyclerRefreshListener());
    }

    public  class OnRecyclerRefreshListener implements RefreshRecyclerView.OnRefreshListener {
        @Override
        public void onPullDownRefresh() {
//           执行下拉刷新操作，一般是联网更新数据
            datarecommendlist.clear();
            getrecommend();
            page = 1;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new MyhttpUtils(context, new MyhttpUtils.GetStringBack() {
                        @Override
                        public void getString(String str) {
                            dataattentionlist.clear();
                            try {
                                JSONObject jsonObject = new JSONObject(str);
                                //下载数据到list中
                                if (jsonObject.getString("success").equals("true")) {
                                    JSONArray jsonArray = jsonObject.getJSONArray("moveList");
                                    int j = jsonArray.length();
                                    for (int i = 0; i < j; i++) {
                                        dataattentionlist.add(jsonArray.getJSONObject(i));
                                    }
                                    attentionAdapter.notifyDataSetChanged();
                                    headerAndFooterWrapper.notifyDataSetChanged();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }).download(attentionpath + 1 +userid);
                    SystemClock.sleep(1000);
                    handler.sendEmptyMessage(REFRESH);
                }
            }).start();

        }
        @Override
        public void onLoadingMore() {
//            执行上拉加载操作，一般是联网请求更多分页数据

                page += 1;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new MyhttpUtils(context, new MyhttpUtils.GetStringBack() {
                            @Override
                            public void getString(String str) {
                                try {
                                    JSONObject jsonObject = new JSONObject(str);
                                    //下载数据到list中
                                    if (jsonObject.getString("success").equals("true")) {
                                        JSONArray jsonArray = jsonObject.getJSONArray("moveList");
                                        int j = jsonArray.length();
                                        for (int i = 0; i < j; i++) {
                                            dataattentionlist.add(jsonArray.getJSONObject(i));
                                        }
                                    }
                                    attentionAdapter.notifyDataSetChanged();
                                    headerAndFooterWrapper.notifyDataSetChanged();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).download(attentionpath +page + userid);
                        SystemClock.sleep(1000);
                        handler.sendEmptyMessage(LOADMORE);
                    }
                }).start();

        }

    }
}
