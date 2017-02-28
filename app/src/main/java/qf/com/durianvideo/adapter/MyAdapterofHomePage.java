package qf.com.durianvideo.adapter;

import android.content.Context;
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

public class MyAdapterofHomePage extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context mContext;
    List<String> datas=new ArrayList<String>();
    public MyAdapterofHomePage( Context mContext, List<String> datas){
        this.mContext = mContext;
        this.datas = datas;
    }

    /**
     * 创建ViewHolder这个模板的
     * @param parent
     * @param viewType ——这个参数是实现多头部的关键
     * @return
     */
    @Override
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

    /**
     * 模板类  ----> ListView中的那个模板类
     * 还要用来找控件
     */
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
    }
}
