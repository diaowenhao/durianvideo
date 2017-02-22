package qf.com.durianvideo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import qf.com.durianvideo.R;
import qf.com.durianvideo.adapter.MyAdapterOfChannel;
import qf.com.durianvideo.util.MyhttpUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelFragment extends Fragment {

   public  GridView  channel_gv_channel;
    private List<JSONObject> list=new ArrayList<>();
    private String url="http://www.liulianvideo.com:8088/filmDataSys/movieType/getTypeData.html";
    private  MyAdapterOfChannel  myadapter;
   private  Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_channel, container, false);
        channel_gv_channel= (GridView) view.findViewById(R.id.channel_gv_channel);
        myadapter=new MyAdapterOfChannel(list,context);
        channel_gv_channel.setAdapter(myadapter);

         initData();


        Log.e("----=",list.toString());


        return view;
    }

    private void initData() {

     new MyhttpUtils(context, new MyhttpUtils.GetStringBack() {
         @Override
         public void getString(String str) {
             if (str.equals("") || str.equals("null")){
                  Toast.makeText(context,"服务器打盹了",Toast.LENGTH_SHORT).show();
             }
             else
             {
                 try {
                     JSONObject json=new JSONObject(str);
                     JSONArray  jsonarray=json.getJSONArray("movieTypes");
                     Log.e("===-",jsonarray.toString());
                     int length=jsonarray.length();

                     for (int i=0;i<length;i++){

                         list.add(jsonarray.getJSONObject(i));
                     }
                     myadapter.notifyDataSetChanged();
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }

             }
         }
     }).getString(url);


    }


}
