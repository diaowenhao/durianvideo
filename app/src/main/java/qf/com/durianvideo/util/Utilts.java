package qf.com.durianvideo.util;

import android.content.Context;
import android.content.Intent;

import qf.com.durianvideo.activity.HomePageActivity;
import qf.com.durianvideo.activity.SearchActivity;

/**
 * Created by Administrator on 2017/2/23.
 */

public class Utilts {
    //不带参数的页面跳转
    public  static void  AtoBskip(Context context,Class c){

        Intent intent=new Intent(context,c);
        context.startActivity(intent);
    }


}
