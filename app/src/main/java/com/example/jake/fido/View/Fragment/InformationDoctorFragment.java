package com.example.jake.fido.View.Fragment;


import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devs.readmoreoption.ReadMoreOption;
import com.example.jake.fido.R;


public class InformationDoctorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ReadMoreOption readMoreOption;
    LinearLayout ll_container;
    TextView tv_information;
    FrameLayout fl_information,fl_workplace,fl_expierence,fl_reward;
    LinearLayout fl_regency;
    View mainView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public InformationDoctorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformationDoctorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InformationDoctorFragment newInstance(String param1, String param2) {
        InformationDoctorFragment fragment = new InformationDoctorFragment();
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
        createData();
    }
    private void createData() {
        readMoreOption = new ReadMoreOption.Builder(getContext())
                .textLength(200)
                .moreLabel("Đọc Thêm")
                .lessLabel("Ẩn đi")
                .moreLabelColor(Color.RED)
                .lessLabelColor(Color.BLUE)
                .labelUnderLine(true)
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView=inflater.inflate(R.layout.fragment_information_doctor, container, false);
        fl_information = (FrameLayout) mainView.findViewById(R.id.layout_information);
        fl_regency = (LinearLayout) mainView.findViewById(R.id.layout_regency);
        fl_workplace = (FrameLayout) mainView.findViewById(R.id.layout_workplace);
        fl_expierence = (FrameLayout) mainView.findViewById(R.id.layout_expierence);
        fl_reward = (FrameLayout) mainView.findViewById(R.id.layout_reward);
        ll_container = (LinearLayout)mainView.findViewById(R.id.ll_container);
        View infor_view =  getLayoutInflater().inflate(R.layout.doctor_infor,null);
        tv_information = (TextView)infor_view.findViewById(R.id.tv_infor);


        for(int i=0;i<5;i++) {
            View infor_view_element =  getLayoutInflater().inflate(R.layout.doctor_element_inside,fl_regency,false);
            fl_regency.addView(infor_view_element);

        }
        readMoreOption.addReadMoreTo(tv_information,getContext().getResources().getString(R.string.gioithieutest));
        fl_information.addView(infor_view);
        return mainView;
    }

    // TODO: Rename method, update argument and hook method into UI event




    @Override
    public void onDetach() {
        super.onDetach();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
