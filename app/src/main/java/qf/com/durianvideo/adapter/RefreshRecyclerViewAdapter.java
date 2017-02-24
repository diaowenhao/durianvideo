package qf.com.durianvideo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import qf.com.durianvideo.R;
import qf.com.durianvideo.util.CircleTransform;

/**
 * Created by Administrator on 2017/2/23.
 */

public class RefreshRecyclerViewAdapter extends RecyclerView.Adapter<RefreshRecyclerViewAdapter.myViewHolder> {

    private Context mContext;
    private List<JSONObject> datas=new ArrayList<>();
//    public static final int TYPE_1 = 1;
//    public static final int TYPE_2 = 2;
//    public  int type;
    LayoutInflater inflater;
    public RefreshRecyclerViewAdapter(Context mContext,List<JSONObject> datas) {
        this.mContext = mContext;
        this.datas=datas;
        inflater =LayoutInflater.from(mContext);
//        this.type=type;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


//        switch (viewType) {
//            case TYPE_1:
//                view = inflater.inflate(R.layout.videoofsearchitem, parent, false);
//                break;
//            case TYPE_2:
        View     view = inflater.inflate(R.layout.userofsearchitem, parent, false);
//                break;
//        }
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
         final JSONObject data = datas.get(position);

        try {
//                switch (type){
//                    case TYPE_1:
////                        holder.search_video_tv_playtime;
////                        holder.search_video_tv_videolength;
////                        holder.search_video_tv_videoname;
//                        holder.search_video_img.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                            }
//                        });
//                        break;
//                    case TYPE_2:
            Log.d("-=-=",data.toString());
                        holder.search_user_item_tv_name.setText(data.getString("userName").toString().trim());
                        holder.search_user_item_tv_grade.setText("LV."+data.getString("gradeSort").toString().trim());
                        Glide.with(mContext).load(data.getString("imgUrl").toString().trim()).transform(new CircleTransform(mContext)).placeholder(R.drawable.buffer).error(android.R.drawable.ic_menu_close_clear_cancel).into(holder.search_user_item_img_head);
                        holder.search_user_item_img_head.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    Toast.makeText(mContext,data.getString("userId").toString(),Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
//                        break;
//                  }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView  search_video_tv_videoname,search_video_tv_videolength,search_video_tv_playtime,search_user_item_tv_name,search_user_item_tv_grade;
        ImageView search_video_img,search_user_item_img_head;
//        int viewType;
        public myViewHolder( View itemView) {
            super(itemView);
//             this.viewType=viewtype;
//            switch (viewType){
//                case  TYPE_1:
//                    search_video_tv_videoname= (TextView) itemView.findViewById(R.id.search_video_tv_videoname);
//                    search_video_tv_videolength= (TextView) itemView.findViewById(R.id.search_video_tv_videolength);
//                    search_video_tv_playtime= (TextView) itemView.findViewById(R.id.search_video_tv_playtime);
//                    search_video_img= (ImageView) itemView.findViewById(R.id.search_video_img);
//                    break;
//                case  TYPE_2:
                    search_user_item_tv_name= (TextView) itemView.findViewById(R.id.search_user_item_tv_name);
                    search_user_item_tv_grade= (TextView) itemView.findViewById(R.id.search_user_item_tv_grade);
                    search_user_item_img_head= (ImageView) itemView.findViewById(R.id.search_user_item_img_head);
//                    break;
//            }
        }
    }


}
