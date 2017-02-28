package qf.com.durianvideo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import qf.com.durianvideo.R;

/**
 * Created by lenovo on 2017/2/28.
 */

public class ImgAdapter extends PagerAdapter {
    private List<Integer> list;
    private Context context;

    public ImgAdapter(List<Integer> list, Context context) {
        super();
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    //如果getCount返回的数字是无限大，position这个位置信息范围：0～（无限大－1）
    //所以如果还继续调用list.get（position）这个方法就会造成链表下标越界问题
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.i("info", "=========="+position);
        ImageView img = new ImageView(context);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        if (list.size() != 0) {
            img.setImageResource(list.get(position % list.size()));
        }else {
            img.setImageResource(R.mipmap.ic_launcher);
        }

        container.addView(img);

        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((ImageView)object);
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
