package qf.com.durianvideo.util;


import android.content.Context;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by Administrator on 2017/2/22.
 */

public class MyhttpUtils  {

    Context  context;
  private   HttpUtils httpUtils ;
   private   GetStringBack getStringBack;
    public   MyhttpUtils(Context  context,GetStringBack getStringBack){
        this.context=context;
        this.getStringBack=getStringBack;
    }
    public  void   download(final String url){
        httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, null, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                getStringBack.getString(responseInfo.result);
            }
            @Override
            public void onFailure(HttpException error, String msg) {
               download(url);
            }
        });
    }

  public interface   GetStringBack{
      public  void   getString(String  str);
  }


}

