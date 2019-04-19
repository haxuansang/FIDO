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

import com.example.jake.fido.Objects.ReviewObject;
import com.example.jake.fido.R;
import com.example.jake.fido.View.Adapter.AdapterReviewDoctor;

import java.util.ArrayList;
import java.util.List;


public class ReviewDoctorFragment extends Fragment {
    View view;
    RecyclerView rv_review;
    AdapterReviewDoctor adapterReviewDoctor;
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
        rv_review  = (RecyclerView)view.findViewById(R.id.rv_review);
        List<ReviewObject> listReview = new ArrayList<>();
        for (int i=0;i<10;i++)
            listReview.add(new ReviewObject());
        adapterReviewDoctor = new AdapterReviewDoctor(listReview,getContext(),getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_review.setLayoutManager(linearLayoutManager);
        rv_review.setAdapter(adapterReviewDoctor);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event




}
