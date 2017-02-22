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
        }
        return new MyViewHoldera(view, viewType);
    }

    @Override
    public void onBindViewHolder(MyViewHoldera holder, int position) {


        JSONObject jsonObject = attentionlist.get(position);

        int type = getItemViewType(position);
        switch (type){
            case TYPE_1:

                break;
            case TYPE_2:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder.recommendlayout_horizontal.setLayoutManager(linearLayoutManager);
                //设置适配器
                break;
        }







    }

    @Override
    public int getItemViewType(int position) {

        int length=attentionlist.size();
          if (length>5  && position==5)
          {

              return  TYPE_2;

          }
        else {
               if (position>length)
               {
                   return  TYPE_2;
               }
          }

        return  TYPE_1;
    }

    @Override
    public int getItemCount() {
        return (attentionlist.size()+1);
    }

    class  MyViewHoldera extends RecyclerView.ViewHolder{
           ImageView attentionitem_imge_video,attentionitem_imge_head;
           TextView  attentionitem_tv_name,attentionitem_tv_grade,attentionitem_tv_videoname,attentionitem_tv_playtime,attentionitem_tv_messages,attentionitem_tv_videolength;
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
                    break;


            }




        }
    }
}
