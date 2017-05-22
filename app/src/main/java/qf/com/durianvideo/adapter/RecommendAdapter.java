package qf.com.durianvideo.adapter;

import android.content.Context;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import qf.com.durianvideo.R;
import qf.com.durianvideo.util.CircleTransform;

/**
 * Created by Administrator on 2017/2/27.
 */

public class RecommendAdapter   extends RecyclerView.Adapter<RecommendAdapter.myViewHolder>{

    private List<JSONObject> recommendlist;
    private Context context;
    LayoutInflater inflater;

    public  RecommendAdapter(List<JSONObject> recommendlist,Context context){
           this.recommendlist=recommendlist;
           this.context=context;
         inflater=LayoutInflater.from(context);
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view=inflater.inflate(R.layout.recommendlayoutitem,parent,false);
        return  new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
       final JSONObject jsonObject= recommendlist.get(position);
        try {

            holder.recommendlayout_item_tv_name.setText(jsonObject.getString("userName").toString().trim());
            holder.recommendlayout_item_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Toast.makeText(context,jsonObject.getString("userId").toString().trim(),Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });
            Glide.with(context).load(jsonObject.getString("imgUrl").toString().trim()).transform(new CircleTransform(context)).placeholder(R.drawable.buffer).error(android.R.drawable.ic_menu_close_clear_cancel).into(holder.recommendlayout_item_img_head);
            holder.recommendlayout_item_img_head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Toast.makeText(context,jsonObject.getString("userId").toString().trim(),Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });



        } catch (JSONException e) {
            e.printStackTrace();
        }






    }
    @Override
    public int getItemCount() {
        return recommendlist.size();
    }

    class  myViewHolder extends RecyclerView.ViewHolder{
     ImageView recommendlayout_item_img_head;
     TextView  recommendlayout_item_tv_name;
     Button  recommendlayout_item_btn;


        public myViewHolder(View itemView) {
            super(itemView);
            recommendlayout_item_img_head= (ImageView) itemView.findViewById(R.id.recommendlayout_item_img_head);
            recommendlayout_item_tv_name= (TextView) itemView.findViewById(R.id.recommendlayout_item_tv_name);
            recommendlayout_item_btn= (Button) itemView.findViewById(R.id.recommendlayout_item_btn);
        }
    }
}
