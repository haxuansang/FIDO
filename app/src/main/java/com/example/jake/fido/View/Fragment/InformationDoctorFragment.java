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
import com.example.jake.fido.DetailDoctorActivity;
import com.example.jake.fido.Instance.APIFido;
import com.example.jake.fido.Instance.FidoData;
import com.example.jake.fido.MapsActivity;
import com.example.jake.fido.R;
import com.example.jake.fido.Retrofit.ObjectRetrofit.Doctor;


public class InformationDoctorFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ReadMoreOption readMoreOption;
    LinearLayout ll_container;
    TextView tv_information,tv_phone,tv_address,tv_email;
    FrameLayout fl_information;
    LinearLayout fl_regency, fl_workplace, fl_expierence;
    View mainView;
    CardView cv_calling,cv_email,cv_maps;
    Doctor doctor;

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
        doctor = FidoData.getInstance().getCurrentDoctor();
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
        cv_email = (CardView) mainView.findViewById(R.id.cv_email);
        cv_maps = (CardView)mainView.findViewById(R.id.cv_maps);
        fl_information = (FrameLayout) mainView.findViewById(R.id.layout_information);
        fl_regency = (LinearLayout) mainView.findViewById(R.id.layout_regency);
        fl_workplace = (LinearLayout) mainView.findViewById(R.id.layout_workplace);
        fl_expierence = (LinearLayout) mainView.findViewById(R.id.layout_expierence);
        ll_container = (LinearLayout) mainView.findViewById(R.id.ll_container);
        tv_phone = (TextView)mainView.findViewById(R.id.tv_phone);
        tv_address = (TextView) mainView.findViewById(R.id.tv_address);
        tv_email = (TextView)mainView.findViewById(R.id.tv_email);
        View infor_view = getLayoutInflater().inflate(R.layout.doctor_infor, null);
        tv_information = (TextView) infor_view.findViewById(R.id.tv_infor);
        addView(fl_regency, doctor.getTitle());
        addView(fl_workplace, doctor.getHospitalName());
        addView(fl_expierence, doctor.getExperience());
        tv_email.setText(doctor.getEmail());
        tv_phone.setText(doctor.getPhoneNumber());
        tv_address.setText(doctor.getAddressDetails() + ", " + doctor.getAddressName());
        readMoreOption.addReadMoreTo(tv_information, doctor.getDescription());
        fl_information.addView(infor_view);
        cv_calling.setOnClickListener(this);
        cv_email.setOnClickListener(this);
        cv_maps.setOnClickListener(this);
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
            case R.id.cv_email:
                doEmailIntent();
                break;
            case R.id.cv_maps:
                openMaps();
        }
    }

    private void openMaps() {
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        startActivity(intent);
    }

    private void doEmailIntent() {
        if(cv_email!=null) {

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", tv_email.getText().toString(), null));
          /*  emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");*/
            startActivity(Intent.createChooser(emailIntent, "Send email"));
        }
    }

    private void doCallIntent() {
        if (cv_calling != null) {
           ;
            String number = tv_phone.getText().toString();
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
