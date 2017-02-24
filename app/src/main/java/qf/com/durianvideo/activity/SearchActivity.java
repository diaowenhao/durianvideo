package qf.com.durianvideo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import qf.com.durianvideo.R;
import qf.com.durianvideo.fragment.UserOfSearchFragment;
import qf.com.durianvideo.fragment.VideoOfSearchFragment;
import qf.com.durianvideo.util.Utilts;

public class SearchActivity extends AppCompatActivity {

    private TextView  search_tv_user,search_tv_video;
    private View  search_v_video,search_v_user;
    private FrameLayout  search_fl_replace;
    private EditText  search_et_search;
    private FragmentManager  manager;
    private FragmentTransaction transaction;
    private UserOfSearchFragment userOfSearchFragment;
    private VideoOfSearchFragment  videoOfSearchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        findid();
        getintent();
        initfragment();
    }

    private void initfragment() {
        userOfSearchFragment=new UserOfSearchFragment();
        videoOfSearchFragment=new VideoOfSearchFragment();

        Bundle bundle = new Bundle();
        bundle.putString("msg", search_et_search.getText().toString().trim());
        userOfSearchFragment.setArguments(bundle);
        videoOfSearchFragment.setArguments(bundle);


        transaction=manager.beginTransaction();
        transaction.add(R.id.search_fl_replace,userOfSearchFragment);
        transaction.add(R.id.search_fl_replace,videoOfSearchFragment);
        transaction.show(videoOfSearchFragment);
        transaction.hide(userOfSearchFragment);
        transaction.commit();


    }
    private void findid() {
        search_tv_user= (TextView) findViewById(R.id.search_tv_user);
        search_tv_video= (TextView) findViewById(R.id.search_tv_video);

        search_v_video=findViewById(R.id.search_v_video);
        search_v_user=findViewById(R.id.search_v_user);

        search_et_search= (EditText) findViewById(R.id.search_et_search);

        search_fl_replace= (FrameLayout) findViewById(R.id.search_fl_replace);

        manager=getSupportFragmentManager();
    }
    public  void   saerchOnClick(View v){
        switch (v.getId()){
            case  R.id.search_tv_back:
                Utilts.AtoBskip(SearchActivity.this,HomePageActivity.class);
                finish();
                break;
            case  R.id.search_tv_search:
                //点击搜索后重新初始化碎片，并传值
                initfragment();
                //通过video下划线的显示与隐藏判断当前显示的碎片并更新界面
                videoselect();
               break;
            case R.id.search_tv_video:
                search_tv_video.setTextColor(Color.rgb(255,128,128));
                search_tv_user.setTextColor(Color.rgb(0,0,0));
                search_v_video.setVisibility(View.VISIBLE);
                search_v_user.setVisibility(View.GONE);

                transaction=manager.beginTransaction();
                transaction.show(videoOfSearchFragment);
                transaction.hide(userOfSearchFragment);
                transaction.commit();
                break;
            case R.id.search_tv_user:
                search_tv_video.setTextColor(Color.rgb(0,0,0));
                search_tv_user.setTextColor(Color.rgb(255,128,128));
                search_v_video.setVisibility(View.GONE);
                search_v_user.setVisibility(View.VISIBLE);
                transaction=manager.beginTransaction();
                transaction.show(userOfSearchFragment);
                transaction.hide(videoOfSearchFragment);
                transaction.commit();
                break;
        }
    }

    public void getintent() {
        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        search_et_search.setText(search);
    }

  public void  videoselect(){
      if (search_v_video.getVisibility()==View.VISIBLE  && search_v_user.getVisibility()==View.GONE){
          saerchOnClick(search_tv_video);
      }else
      {
          saerchOnClick(search_tv_user);
      }
   }
}
