package qf.com.durianvideo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import qf.com.durianvideo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttentionFragment extends Fragment {
private RecyclerView attentionFragment_rv;

    public AttentionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view=inflater.inflate(R.layout.fragment_attention, container, false);

        attentionFragment_rv= (RecyclerView) view.findViewById(R.id.attentionFragment_rv);
       attentionFragment_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

}
