package com.example.jake.fido.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.devs.readmoreoption.ReadMoreOption;
import com.example.jake.fido.DetailDoctorActivity;
import com.example.jake.fido.Objects.DoctorObjects;
import com.example.jake.fido.R;
import com.example.jake.fido.Utils.InfiniteScrollListener;
import com.example.jake.fido.Utils.ItemClickListener;
import com.example.jake.fido.Utils.TransitionItemClickListener;
import com.example.jake.fido.View.Adapter.AdapterDoctors;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.jake.fido.Utils.Constants.DOCTOR_IMAGE_TRANSITION_NAME;
import static com.example.jake.fido.Utils.Constants.DOCTOR_ITEM;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DoctorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DoctorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorFragment extends Fragment implements TransitionItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private OnFragmentInteractionListener mListener;
    private RecyclerView rvDoctors;
    AdapterDoctors adapterDoctors;
    ArrayList<DoctorObjects> listFakeDoctors = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    FloatingActionButton floatingActionButton;
    private RelativeLayout rl_loadingmore;


    public DoctorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DoctorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoctorFragment newInstance() {
        DoctorFragment fragment = new DoctorFragment();
        Bundle args = new Bundle();
       /* args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
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
        view = inflater.inflate(R.layout.fragment_doctor, container, false);
        rvDoctors = (RecyclerView) view.findViewById(R.id.rv_doctors);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        rl_loadingmore = (RelativeLayout) view.findViewById(R.id.rl_loading_more);
        fakeData();
        adapterDoctors = new AdapterDoctors(getActivity(), getContext(), listFakeDoctors);
        adapterDoctors.registerItemClickListener(this);
        rvDoctors.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getContext(), 1);
        rvDoctors.setLayoutManager(gridLayoutManager);
        rvDoctors.setAdapter(adapterDoctors);
        rvDoctors.addOnScrollListener(new InfiniteScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                rl_loadingmore.setVisibility(View.VISIBLE);
                fakeData();
                adapterDoctors.notifyDataSetChanged();
            }
        });


        return view;
    }

    private void fakeData() {
        listFakeDoctors.add(new DoctorObjects("Bs. Ngô Văn Võ", "Tâm thần", "Đánh giá 4.5 sao dựa trên 1220 lươt bình chọn", "Kiệt 82, Nguyễn Lương Bằng, Liên Chiểu, Đà Nẵng ",
                "https://cdn.tuoitre.vn/thumb_w/640/2018/2/10/bac-si-tran-huynh-151824259749249048360.jpg", (int) 4.5));
        listFakeDoctors.add(new DoctorObjects("Bs. Ngô Văn Võ", "Tâm thần", "Đánh giá 4.5 sao dựa trên 1220 lươt bình chọn", "Kiệt 82, Nguyễn Lương Bằng, Liên Chiểu, Đà Nẵng ",
                "https://media.doisongvietnam.vn/u/rootimage/editor/2018/02/27/13/21/s21519690899_1206.jpg", (int) 4.5));
        listFakeDoctors.add(new DoctorObjects("Bs. Ngô Văn Võ", "Tâm thần", "Đánh giá 4.5 sao dựa trên 1220 lươt bình chọn", "Kiệt 82, Nguyễn Lương Bằng, Liên Chiểu, Đà Nẵng ",
                "https://cdn.tuoitre.vn/thumb_w/640/2018/2/10/bac-si-tran-huynh-151824259749249048360.jpg", (int) 4.5));
        listFakeDoctors.add(new DoctorObjects("Bs. Ngô Văn Võ", "Tâm thần", "Đánh giá 4.5 sao dựa trên 1220 lươt bình chọn", "Kiệt 82, Nguyễn Lương Bằng, Liên Chiểu, Đà Nẵng ",
                "https://media.doisongvietnam.vn/u/rootimage/editor/2018/02/27/13/21/s21519690899_1206.jpg", (int) 4.5));
        listFakeDoctors.add(new DoctorObjects("Bs. Ngô Văn Võ", "Tâm thần", "Đánh giá 4.5 sao dựa trên 1220 lươt bình chọn", "Kiệt 82, Nguyễn Lương Bằng, Liên Chiểu, Đà Nẵng ",
                "https://cdn.tuoitre.vn/thumb_w/640/2018/2/10/bac-si-tran-huynh-151824259749249048360.jpg", (int) 4.5));
        listFakeDoctors.add(new DoctorObjects("Bs. Ngô Văn Võ", "Tâm thần", "Đánh giá 4.5 sao dựa trên 1220 lươt bình chọn", "Kiệt 82, Nguyễn Lương Bằng, Liên Chiểu, Đà Nẵng ",
                "https://media.doisongvietnam.vn/u/rootimage/editor/2018/02/27/13/21/s21519690899_1206.jpg", (int) 4.5));
        rl_loadingmore.setVisibility(View.GONE);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      /*  if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(int pos, DoctorObjects doctorObjects, CircleImageView sharedImageView) {
        Intent intent = new Intent(getActivity(), DetailDoctorActivity.class);
        intent.putExtra(DOCTOR_ITEM, doctorObjects);
        intent.putExtra(DOCTOR_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(sharedImageView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(),
                sharedImageView,
                ViewCompat.getTransitionName(sharedImageView));

        startActivity(intent, options.toBundle());

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
