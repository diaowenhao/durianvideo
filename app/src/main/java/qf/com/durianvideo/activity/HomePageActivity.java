package qf.com.durianvideo.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import qf.com.durianvideo.R;
import qf.com.durianvideo.fragment.DiscoverFragment;
import qf.com.durianvideo.fragment.HomePageFragment;

public class HomePageActivity extends AppCompatActivity {
    private ImageView homeClickImage;
    private ImageView findClickImage;
    private ImageView competeClickImage;
    private ImageView mineClickImage;

    private TextView homeText;
    private TextView findText;
    private TextView competeText;
    private TextView mineText;
    private DiscoverFragment discoverFragment;

    private FragmentManager fm;
    private Fragment fragment_home_page;
    private   FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //在这其中初始化碎片，对应添加自己的
        init();

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        //刚开始为首页
        ft.replace(R.id.fram_home,fragment_home_page).commit();
    }



    //四个大模块来回跳转
    //0xFFFF7C84
    public void onChooce(View view){
        switch (view.getId()){
            case R.id.home:
                //改变图片颜色
                refreshImage(R.drawable.main_ic_btn,R.drawable.main_find,
                        R.drawable.main_dingyue,R.drawable.main_mine);
                //改变文字颜色
                homeText.setTextColor(0xFFFF7C84);
                findText.setTextColor(0xff000000);
                competeText.setTextColor(0xff000000);
                mineText.setTextColor(0xff000000);
                //然后各自设置碎片
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.fram_home,fragment_home_page).commit();
                break;
            case R.id.find:
                refreshImage(R.drawable.main_ic,R.drawable.main_find_btn,
                        R.drawable.main_dingyue,R.drawable.main_mine);
                homeText.setTextColor(0xff000000);
                findText.setTextColor(0xFFFF7C84);
                competeText.setTextColor(0xff000000);
                mineText.setTextColor(0xff000000);
                //然后各自设置碎片
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.fram_home,discoverFragment).commit();
                break;
            case R.id.compete:
                refreshImage(R.drawable.main_ic,R.drawable.main_find,
                        R.drawable.main_dingyue_btn,R.drawable.main_mine);
                homeText.setTextColor(0xff000000);
                findText.setTextColor(0xff000000);
                competeText.setTextColor(0xFFFF7C84);
                mineText.setTextColor(0xff000000);
                //然后各自设置碎片
                break;
            case R.id.mine:
                refreshImage(R.drawable.main_ic,R.drawable.main_find,
                        R.drawable.main_dingyue,R.drawable.main_mine_btn);
                homeText.setTextColor(0xff000000);
                findText.setTextColor(0xff000000);
                competeText.setTextColor(0xff000000);
                mineText.setTextColor(0xFFFF7C84);
                //然后各自设置碎片
                break;
        }
    }

    private void refreshImage(int id1,int id2, int id3, int id4) {
        homeClickImage.setImageResource(id1);
        findClickImage.setImageResource(id2);
        competeClickImage.setImageResource(id3);
        mineClickImage.setImageResource(id4);
    }

    private void init() {
        homeClickImage = (ImageView) findViewById(R.id.home_click);
        findClickImage = (ImageView) findViewById(R.id.find_click);
        competeClickImage = (ImageView) findViewById(R.id.compete_click);
        mineClickImage = (ImageView) findViewById(R.id.mine_click);

        homeText = (TextView) findViewById(R.id.home_text);
        findText = (TextView) findViewById(R.id.find_text);
        competeText = (TextView) findViewById(R.id.compete_text);
        mineText = (TextView) findViewById(R.id.mine_text);
        homeText.setTextColor(0xFFFF7C84);

        //初始化碎片，对应添加自己的
        fragment_home_page = new HomePageFragment();
         //发现功能的碎片
        discoverFragment=new DiscoverFragment();
    }
}
