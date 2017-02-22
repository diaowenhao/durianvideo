package qf.com.durianvideo.fragment;



import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import qf.com.durianvideo.R;
import qf.com.durianvideo.activity.SearchActivity;
import qf.com.durianvideo.adapter.MyAdapterofDiscover;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment  implements ViewPager.OnPageChangeListener,View.OnClickListener{
    private ViewPager discover_viewPager;
    private List<Fragment> pagerList = new ArrayList<Fragment>();
   private  TextView  discover_tv_channel,discover_tv_attention,discover_tv_search;
    private  View discover_v_channel,discover_v_attention,view;
    private EditText discover_et_search;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       view= inflater.inflate(R.layout.fragment_discover, container, false);


        findid();
        setlistent();


        initPager();
        discover_viewPager.setAdapter(new MyAdapterofDiscover(getChildFragmentManager(),pagerList));
        discover_viewPager.addOnPageChangeListener(this);
        return view;

    }

    private void setlistent() {

        discover_tv_channel.setOnClickListener(this);
        discover_tv_attention.setOnClickListener(this);
        discover_tv_search.setOnClickListener(this);
    }

    private void findid() {

        discover_viewPager = (ViewPager) view.findViewById(R.id.discover_viewPager);

        discover_tv_channel= (TextView) view.findViewById(R.id.discover_tv_channel);
        discover_tv_attention= (TextView) view.findViewById(R.id.discover_tv_attention);
        discover_tv_search= (TextView) view.findViewById(R.id.discover_tv_search);

        discover_v_channel=view.findViewById(R.id.discover_v_channel);
        discover_v_attention=view.findViewById(R.id.discover_v_attention);


        discover_et_search= (EditText) view.findViewById(R.id.discover_et_search);


    }

    private void initPager() {
        pagerList.add(new AttentionFragment());
        pagerList.add(new ChannelFragment());
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    if (position==0){
        onClick(discover_tv_attention);
    }
    else
    {
        onClick(discover_tv_channel);
    }

    }

    @Override
    public void onPageScrollStateChanged(int state) {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.discover_tv_attention:
                discover_tv_attention.setTextColor(Color.rgb(255,128,128));
                discover_tv_channel.setTextColor(Color.rgb(0,0,0));
                discover_v_attention.setVisibility(View.VISIBLE);
                discover_v_channel.setVisibility(View.GONE);

                discover_viewPager.setCurrentItem(0);
                break;
            case  R.id.discover_tv_channel:

                discover_tv_attention.setTextColor(Color.rgb(0,0,0));
                discover_tv_channel.setTextColor(Color.rgb(255,128,128));
                discover_v_attention.setVisibility(View.GONE);
                discover_v_channel.setVisibility(View.VISIBLE);

                discover_viewPager.setCurrentItem(1);
                break;
            case  R.id.discover_tv_search:
                Intent  intent =new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("search",discover_et_search.getText().toString().trim());
                startActivity(intent);
                break;

        }
    }
}

