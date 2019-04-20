package com.example.jake.fido.View.Fragment;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devs.readmoreoption.ReadMoreOption;
import com.example.jake.fido.R;


public class InformationDoctorFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ReadMoreOption readMoreOption;
    LinearLayout ll_container;
    TextView tv_information;
    FrameLayout fl_information;
    LinearLayout fl_regency, fl_workplace, fl_expierence, fl_reward;
    View mainView;
    CardView cv_calling;

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

    @TargetApi(Build.VERSION_CODES.M)
    private void createData() {
        readMoreOption = new ReadMoreOption.Builder(getContext())
                .moreLabel("Đọc Thêm")
                .lessLabel("Ẩn đi")
                .moreLabelColor(getContext().getColor(R.color.colorPrimaryDark))
                .lessLabelColor(getContext().getColor(R.color.colorPrimaryDark))
                .labelUnderLine(true)
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_information_doctor, container, false);
        cv_calling = (CardView) mainView.findViewById(R.id.cv_calling);
        fl_information = (FrameLayout) mainView.findViewById(R.id.layout_information);
        fl_regency = (LinearLayout) mainView.findViewById(R.id.layout_regency);
        fl_workplace = (LinearLayout) mainView.findViewById(R.id.layout_workplace);
        fl_expierence = (LinearLayout) mainView.findViewById(R.id.layout_expierence);
        fl_reward = (LinearLayout) mainView.findViewById(R.id.layout_reward);
        ll_container = (LinearLayout) mainView.findViewById(R.id.ll_container);
        View infor_view = getLayoutInflater().inflate(R.layout.doctor_infor, null);
        tv_information = (TextView) infor_view.findViewById(R.id.tv_infor);

        addView(fl_regency, "");
        addView(fl_workplace, "Bệnh Viện Ung Bướu Đà Nẵng");
        addView(fl_reward, "Giải nhất cuộc thi bác sĩ giỏi thành phố Đà Nẵng");
        addView(fl_expierence, "Bác sĩ tại bệnh viện Bình Dân thành phố Đà Nẵng");
        readMoreOption.addReadMoreTo(tv_information, getContext().getResources().getString(R.string.gioithieutest));
        fl_information.addView(infor_view);
        cv_calling.setOnClickListener(this);
        return mainView;
    }

    // TODO: Rename method, update argument and hook method into UI event


    private void addView(ViewGroup view, String text) {
        View infor_view_element = getLayoutInflater().inflate(R.layout.doctor_element_inside, fl_regency, false);
        TextView tv_detail = (TextView) infor_view_element.findViewById(R.id.tv_infor);
        if (!"".equals(text)) {
            tv_detail.setText(text);
        }
        view.addView(infor_view_element);
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_calling:
                doCallIntent();
                break;
        }
    }

    private void doCallIntent() {
        if (cv_calling != null) {
            TextView tv_number = (TextView) cv_calling.findViewById(R.id.tv_calling);
            String number = tv_number.getText().toString();
            String uri = "tel:" + number.trim();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(uri));
            startActivity(intent);
        }
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
