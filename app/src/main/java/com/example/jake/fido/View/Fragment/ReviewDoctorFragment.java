package com.example.jake.fido.View.Fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.jake.fido.Instance.APIFido;
import com.example.jake.fido.Instance.FidoData;
import com.example.jake.fido.Objects.ReviewObject;
import com.example.jake.fido.R;
import com.example.jake.fido.Utils.InfiniteScrollListener;
import com.example.jake.fido.View.Adapter.AdapterReviewDoctor;

import java.util.ArrayList;
import java.util.List;


public class ReviewDoctorFragment extends Fragment {
    View view;
    RecyclerView rv_review;
    public static AdapterReviewDoctor adapterReviewDoctor;
    RelativeLayout rl_loadingmore;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ReviewDoctorFragment() {
        // Required empty public constructor
    }


    public static ReviewDoctorFragment newInstance(String param1, String param2) {
        ReviewDoctorFragment fragment = new ReviewDoctorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_review_doctor, container, false);
        rv_review = (RecyclerView) view.findViewById(R.id.rv_review);
        rl_loadingmore = (RelativeLayout) view.findViewById(R.id.rl_loading_more);
        adapterReviewDoctor = new AdapterReviewDoctor(FidoData.getInstance().getCurrentDoctor().getReview(), getContext(), getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        rv_review.setLayoutManager(gridLayoutManager);
        rv_review.setAdapter(adapterReviewDoctor);
        /*rv_review.addOnScrollListener(new InfiniteScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                rl_loadingmore.setVisibility(View.GONE);
                //add data
                adapterReviewDoctor.notifyDataSetChanged();
            }
        });*/
        return view;
    }

}
