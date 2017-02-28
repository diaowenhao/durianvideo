package qf.com.durianvideo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by lenovo on 2017/2/28.
 */

public class InnerAdapterofHomePage extends BaseRecyclerAdapter{

    public InnerAdapterofHomePage(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, Object data) {

    }
}
