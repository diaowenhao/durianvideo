package qf.com.durianvideo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.headerandfooterwrapper_library.HeaderAndFooterWrapper;
import com.example.refreshrecyclerview_library.RefreshRecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import qf.com.durianvideo.R;
import qf.com.durianvideo.adapter.RefreshRecyclerViewAdapter;
import qf.com.durianvideo.util.MyhttpUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoOfSearchFragment extends Fragment {

    private HeaderAndFooterWrapper headerAndFooterWrapper;
    private RefreshRecyclerViewAdapter recyclerAdapter;
    private RefreshRecyclerView search_user_recyclerview;
    private Context context;
    private   int  page=1;
    private String msg="";
    private  String  userpath="http://www.liulianvideo.com:8088/filmDataSys/queryController/queryMovieByKeyword.html?pageNum="+page+"&keyWord=";
    private List<JSONObject> datalist=new ArrayList<>();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_video_of_search, container, false);
        search_user_recyclerview = (RefreshRecyclerView)view.findViewById(R.id.search_video_recyclerview);
        //获取到宿主activity传递过来的数据
        Bundle bundle = getArguments();
        if (bundle != null) {
            msg = bundle.getString("msg");
            initdata(userpath+msg);
        }
        initRefreshRecyclerView();
        return view;
    }

    private void initdata(String url) {

        new MyhttpUtils(context, new MyhttpUtils.GetStringBack() {
            @Override
            public void getString(String str) {

            //得到数据并加入到list中
                recyclerAdapter.notifyDataSetChanged();
            }
        }).download(url);


    }

    private void initRefreshRecyclerView() {

        recyclerAdapter = new RefreshRecyclerViewAdapter(context,datalist);
        headerAndFooterWrapper = new HeaderAndFooterWrapper(recyclerAdapter);
//        设置布局管理器
        search_user_recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        search_user_recyclerview.setAdapter(headerAndFooterWrapper);
        search_user_recyclerview.addHeaderView(search_user_recyclerview.getHeaderView(), headerAndFooterWrapper);
        search_user_recyclerview.addFooterView(search_user_recyclerview.getFooterView(), headerAndFooterWrapper);
        search_user_recyclerview.setOnRefreshListener(new   OnRecyclerRefreshListener());
    }

    public  class OnRecyclerRefreshListener implements RefreshRecyclerView.OnRefreshListener {
        @Override
        public void onPullDownRefresh() {
//           执行下拉刷新操作，一般是联网更新数据


            recyclerAdapter.notifyDataSetChanged();
        }

        @Override
        public void onLoadingMore() {
//            执行上拉加载操作，一般是联网请求更多分页数据


            recyclerAdapter.notifyDataSetChanged();
        }
    }


}
