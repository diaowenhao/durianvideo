package qf.com.durianvideo.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import qf.com.durianvideo.R;

/**
 * Created by Administrator on 2017/2/21.
 */

public class MyAdapterOfAttention  extends RecyclerView.Adapter<MyAdapterOfAttention.MyViewHoldera> {
  private  List<JSONObject>  attentionlist;
    private List<JSONObject>  recommendlist;
   private  Context context;
    public static final int TYPE_0 = 0;
    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;
    LayoutInflater inflater;
   public  MyAdapterOfAttention (List<JSONObject> attentionlist, Context context,List<JSONObject>  recommendlist){
       this.attentionlist=attentionlist;
       this.context=context;
       this.recommendlist=recommendlist;
       inflater =LayoutInflater.from(context);
   }

    @Override
    public MyViewHoldera onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TYPE_1:
                view = inflater.inflate(R.layout.attentionlayoutitem, parent, false);
                break;
            case TYPE_2:
                view = inflater.inflate(R.layout.recommendlayout, parent, false);
                break;
            case TYPE_0:
                view = inflater.inflate(R.layout.emplayout, parent, false);
                break;
        }
        return new MyViewHoldera(view, viewType);
    }

    @Override
    public void onBindViewHolder(MyViewHoldera holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case TYPE_1:
                if (attentionlist.size()>0){
                    try {
                        JSONObject jsonObject = attentionlist.get(position);
                        Glide.with(context).load(jsonObject.getString("path").toString().trim()).placeholder(R.drawable.buffer).error(android.R.drawable.ic_menu_close_clear_cancel).into(holder.attentionitem_imge_head);
                        holder. attentionitem_imge_head.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        Glide.with(context).load(jsonObject.getString("path").toString().trim()).placeholder(R.drawable.buffer).error(android.R.drawable.ic_menu_close_clear_cancel).into(holder.attentionitem_imge_video);
                        holder.attentionitem_imge_video.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                            }
                        });

//                    holder. attentionitem_tv_name.setText();
//                    holder.attentionitem_tv_grade.setText();
//                    holder.attentionitem_tv_videoname.setText();
//                    holder.attentionitem_tv_playtime.setText();
//                    holder.attentionitem_tv_messages.setText();
//                    holder.attentionitem_tv_videolength.setText();
                        holder.attentionitem_lL_comment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {



                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                break;
            case TYPE_2:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder.recommendlayout_horizontal.setLayoutManager(linearLayoutManager);
                //设置适配器
                holder.recommendlayout_horizontal.setAdapter(new RecommendAdapter(recommendlist,context));
                holder.recommendlayout_tv_more.setVisibility(View.VISIBLE);
                holder.recommendlayout_tv_g.setVisibility(View.VISIBLE);
                holder.recommendlayout_tv_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转到小主推荐
                    }
                });
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {

        int length=attentionlist.size();
        int  size=recommendlist.size();

        if (size == 0){
            if (length==0){
                return 0;
            }
            else
            {
                return TYPE_1;
            }
        }else
         {
            if (length==0){
                return TYPE_2;
            }
            else if ((length>5  &&  position==5)  || (length<5 && position>length))
            {
                return TYPE_2;
            }
            else {
                return  TYPE_1;
            }
        }




    }

    @Override
    public int getItemCount() {
        return (attentionlist.size()+1);
    }

    class  MyViewHoldera extends RecyclerView.ViewHolder{
           ImageView attentionitem_imge_video,attentionitem_imge_head;
           TextView  attentionitem_tv_name,attentionitem_tv_grade,attentionitem_tv_videoname,attentionitem_tv_playtime,attentionitem_tv_messages,attentionitem_tv_videolength,recommendlayout_tv_more,recommendlayout_tv_g;
           LinearLayout attentionitem_lL_comment;
           RecyclerView  recommendlayout_horizontal;
           int viewType;
        public MyViewHoldera(View itemView,int viewType) {
            super(itemView);
            this.viewType=viewType;
            switch (viewType){
                case  TYPE_1:
                    attentionitem_imge_video= (ImageView) itemView.findViewById(R.id.attentionitem_imge_video);
                    attentionitem_imge_head= (ImageView) itemView.findViewById(R.id.attentionitem_imge_head);
                    attentionitem_tv_name= (TextView) itemView.findViewById(R.id.attentionitem_tv_name);
                    attentionitem_tv_grade= (TextView) itemView.findViewById(R.id.attentionitem_tv_grade);
                    attentionitem_tv_videoname= (TextView) itemView.findViewById(R.id.attentionitem_tv_videoname);
                    attentionitem_tv_playtime= (TextView) itemView.findViewById(R.id.attentionitem_tv_playtime);
                    attentionitem_tv_messages= (TextView) itemView.findViewById(R.id.attentionitem_tv_messages);
                    attentionitem_tv_videolength= (TextView) itemView.findViewById(R.id.attentionitem_tv_videolength);

                    attentionitem_lL_comment= (LinearLayout) itemView.findViewById(R.id.attentionitem_lL_comment);
                    break;
                case   TYPE_2:
                    recommendlayout_horizontal= (RecyclerView) itemView.findViewById(R.id.recommendlayout_horizontal);
                    recommendlayout_tv_more= (TextView) itemView.findViewById(R.id.recommendlayout_tv_more);
                    recommendlayout_tv_g= (TextView) itemView.findViewById(R.id.recommendlayout_tv_g);
                    break;
               case TYPE_0:
                    break;
            }
        }
    }
}
