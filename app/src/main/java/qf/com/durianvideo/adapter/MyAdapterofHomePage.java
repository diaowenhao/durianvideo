package qf.com.durianvideo.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import qf.com.durianvideo.R;

/**
 * Created by lenovo on 2017/2/27.
 */

public class MyAdapterofHomePage extends BaseRecyclerAdapter<String>{
    Context mContext;
    List<String> datas=new ArrayList<String>();

    InnerAdapterofHomePage mInnerAdapterofHomePage;

    public MyAdapterofHomePage( Context mContext, List<String> datas){
        super(mContext);
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.homepage_normal_item, parent, false);
        //加载item并且返回
        return new MyHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, String data) {
        if(viewHolder instanceof MyHolder) {
            ((MyHolder) viewHolder).text.setText(datas.get(RealPosition));
        }
    }

    class MyHolder extends BaseRecyclerAdapter.Holder {
        TextView text;
        RecyclerView mRecyclerView;
        public MyHolder(View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(R.id.text1);
        mRecyclerView = (RecyclerView) itemView.findViewById(R.id.homepage_recycler);
        initData();
            //设置成列表显示的
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            //设置适配器---还没有整完
            mInnerAdapterofHomePage = new InnerAdapterofHomePage(mContext);
        mRecyclerView.setAdapter(mInnerAdapterofHomePage);
    }

        public void initData(){}
}
    /**
     * 创建ViewHolder这个模板的
     * @param parent
     * @param viewType ——这个参数是实现多头部的关键
     * @return
     */
   /* @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(mContext).inflate(R.layout.homepage_normal_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        viewHolder.getmTextView3().setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    *//**
     * 模板类  ----> ListView中的那个模板类
     * 还要用来找控件
     *//*
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView1;
        TextView mTextView2;
        TextView mTextView3;
        ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView1= (TextView) itemView.findViewById(R.id.text1);
            mTextView2= (TextView) itemView.findViewById(R.id.text2);
            mTextView3= (TextView) itemView.findViewById(R.id.text3);
            mImageView = (ImageView) itemView.findViewById(R.id.image_aa);
        }

        public TextView getmTextView1() {
            return mTextView1;
        }
        public TextView getmTextView2() {
            return mTextView2;
        }
        public TextView getmTextView3() {
            return mTextView3;
        }
        public ImageView getmImageView() {
            return mImageView;
        }
    }*/
}
