package qf.com.durianvideo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import qf.com.durianvideo.R;
import qf.com.durianvideo.util.CircleTransform;
import qf.com.durianvideo.util.CornersTransform;

/**
 * Created by Administrator on 2017/2/22.
 */

public class MyAdapterOfChannel extends BaseAdapter{
  private  List<JSONObject> channellist;
    private Context context;

    public   MyAdapterOfChannel(List<JSONObject> channellist,Context context){

          this.channellist=channellist;
         this.context=context;

    }

    @Override
    public int getCount() {
        return channellist.size();
    }

    @Override
    public Object getItem(int position) {
        return channellist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder   myholder=null;
        if (convertView==null){
              convertView= LayoutInflater.from(context).inflate(R.layout.channeitem,parent,false);
            myholder=new MyHolder();

            myholder.channelitem_img_channel= (ImageView) convertView.findViewById(R.id.channelitem_img_channel);
            myholder.channelitem_tv_channel= (TextView) convertView.findViewById(R.id.channelitem_tv_channel);

            convertView.setTag(myholder);

        }else {

              myholder= (MyHolder) convertView.getTag();

        }

       JSONObject jsonObject= channellist.get(position);

        try {
            myholder.channelitem_tv_channel.setText(jsonObject.getString("typeName").trim());
            myholder.channelitem_tv_channel.setTag(jsonObject.getString("typeId"));
            Glide.with(context).load(jsonObject.getString("typePic").trim()).placeholder(R.mipmap.ic_launcher).transform(new CornersTransform(context,5)).error(android.R.drawable.ic_menu_close_clear_cancel).into(myholder.channelitem_img_channel);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return convertView;
    }

    class   MyHolder{
       ImageView  channelitem_img_channel;
        TextView  channelitem_tv_channel;
    }

}
