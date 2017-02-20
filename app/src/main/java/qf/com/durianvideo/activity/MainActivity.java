package qf.com.durianvideo.activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import qf.com.durianvideo.R;
import qf.com.durianvideo.fragment.DiscoverFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private DiscoverFragment discoverFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        discoverFragment=new DiscoverFragment();

        manager=getFragmentManager();
        transaction=manager.beginTransaction();
        transaction.add(R.id.main_fl_replace,discoverFragment);
        transaction.show(discoverFragment);
        transaction.commit();


    }
}
