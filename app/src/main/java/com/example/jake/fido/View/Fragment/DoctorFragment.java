package com.example.jake.fido.View.Fragment;

import android.app.Dialog;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.jake.fido.DetailDoctorActivity;
import com.example.jake.fido.Instance.APIFido;
import com.example.jake.fido.Instance.FidoData;
import com.example.jake.fido.MainActivity;
import com.example.jake.fido.R;
import com.example.jake.fido.Retrofit.ObjectRetrofit.Doctor;
import com.example.jake.fido.Retrofit.ObjectRetrofit.Doctors;
import com.example.jake.fido.Utils.InfiniteScrollListener;
import com.example.jake.fido.Utils.OnSearchClickListener;
import com.example.jake.fido.Utils.TransitionItemClickListener;
import com.example.jake.fido.View.Adapter.AdapterDoctors;
import com.example.jake.fido.View.Adapter.AdapterSpinner;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
public class DoctorFragment extends Fragment implements TransitionItemClickListener, OnSearchClickListener {
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
    ArrayList<Doctor> listFakeDoctors = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    FloatingActionButton floatingActionButton;
    private RelativeLayout rl_loadingmore;
    private AdapterSpinner adapterSpinnerMajor;
    private AdapterSpinner adapterSpinnerSort;
    private AdapterSpinner adapterSpinnerAddress;
    int scrollDist = 0;
    boolean isVisible = true;
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
        MainActivity.setOnSearchClickListener(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_doctor, container, false);
        rvDoctors = (RecyclerView) view.findViewById(R.id.rv_doctors);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        rl_loadingmore = (RelativeLayout) view.findViewById(R.id.rl_loading_more);
        getData();
        adapterDoctors = new AdapterDoctors(getActivity(), getContext(), listFakeDoctors);
        adapterDoctors.registerItemClickListener(this);
        gridLayoutManager = new GridLayoutManager(getContext(), 1);
        rvDoctors.setLayoutManager(gridLayoutManager);
        rvDoctors.setAdapter(adapterDoctors);
        rvDoctors.addOnScrollListener(new InfiniteScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (FidoData.getInstance().isLoadMore() && FidoData.getInstance().getTypeShow() == 1) {
                    rl_loadingmore.setVisibility(View.VISIBLE);
                    loadMoreData();
                } else if (FidoData.getInstance().isLoadMore()) {
                    loadMoreSuggesstion(FidoData.getInstance().getSearch(), FidoData.getInstance().getSpecial_id(), FidoData.getInstance().getAddress_id(), String.valueOf(FidoData.getInstance().getCurrentPage() + 1));
                }
            }
        });
        rvDoctors.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) floatingActionButton.hide();
                else floatingActionButton.show();
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSearchDialog();
            }
        });
        return view;
    }

    private void showSearchDialog() {
        Spinner spinnerMajor, spinnerAddress, spinnerSort;
        Button btnSearch;
        Dialog dialogSearch = new Dialog(getContext());
        dialogSearch.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSearch.setContentView(R.layout.searchdialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogSearch.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogSearch.getWindow().setAttributes(lp);
        spinnerSort = (Spinner) dialogSearch.findViewById(R.id.spinner_sort);
        spinnerAddress = (Spinner) dialogSearch.findViewById(R.id.spinner_city_doctor);
        spinnerMajor = (Spinner) dialogSearch.findViewById(R.id.spinner_major_doctor);
        createSpinnerMajor(spinnerSort,0);
        createSpinnerMajor(spinnerMajor,1);
        createSpinnerMajor(spinnerAddress,2);
        btnSearch = (Button) dialogSearch.findViewById(R.id.btnsearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadMoreSuggesstion("",String.valueOf(spinnerMajor.getSelectedItemPosition()),String.valueOf(spinnerAddress.getSelectedItemPosition()),"1");
                listFakeDoctors.clear();
                dialogSearch.dismiss();
            }
        });
        dialogSearch.show();

    }

    private void createSpinnerMajor(Spinner spinner, int type) {
        String[] listMajor;
        ArrayList<String> list = new ArrayList<>();

        switch (type) {
            case 0:
                listMajor = getResources().getStringArray(R.array.listsort);
                for (int i = 0; i < listMajor.length; i++) {
                    list.add(listMajor[i]);
                }
                adapterSpinnerSort = new AdapterSpinner(getContext(), list);
                spinner.setAdapter(adapterSpinnerSort);
                break;

            case 1:
                listMajor = getResources().getStringArray(R.array.listmajor);
                for (int i = 0; i < listMajor.length; i++) {
                    list.add(listMajor[i]);
                }
                adapterSpinnerMajor = new AdapterSpinner(getContext(), list);
                spinner.setAdapter(adapterSpinnerMajor);
                break;
            case 2:
                listMajor = getResources().getStringArray(R.array.listcity);
                for (int i = 0; i < listMajor.length; i++) {
                    list.add(listMajor[i]);
                }
                adapterSpinnerAddress = new AdapterSpinner(getContext(), list);
                spinner.setAdapter(adapterSpinnerAddress);
                break;

        }
    }

    private void loadMoreSuggesstion(String search, String special_id, String address_id, String page) {
        final RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", search).addFormDataPart("special_id", special_id).
                        addFormDataPart("address_id", address_id)
                .build();

        APIFido.getInstance().getSoService().searchDoctors(requestBody, page).enqueue(new Callback<Doctors>() {
            @Override
            public void onResponse(Call<Doctors> call, Response<Doctors> response) {
                listFakeDoctors.addAll(response.body().getData());
                adapterDoctors.notifyDataSetChanged();
                FidoData.getInstance().setCurrentPage(response.body().getMeta().getCurrentPage());
                FidoData.getInstance().setLastPage(response.body().getMeta().getLastPage());

            }

            @Override
            public void onFailure(Call<Doctors> call, Throwable t) {

            }
        });

    }

    private void loadMoreData() {
        APIFido.getInstance().getSoService().getDoctors(String.valueOf(FidoData.getInstance().getCurrentPage() + 1)).enqueue(new Callback<Doctors>() {
            @Override
            public void onResponse(Call<Doctors> call, Response<Doctors> response) {
                listFakeDoctors.addAll(response.body().getData());
                adapterDoctors.notifyDataSetChanged();
                rl_loadingmore.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Doctors> call, Throwable t) {
                rl_loadingmore.setVisibility(View.GONE);
            }
        });
    }

    private void getData() {
        if(APIFido.getInstance().getSoService()!=null)
        APIFido.getInstance().getSoService().getDoctors("1").enqueue(new Callback<Doctors>() {
            @Override
            public void onResponse(Call<Doctors> call, Response<Doctors> response) {
                listFakeDoctors.addAll(response.body().getData());
                adapterDoctors.notifyDataSetChanged();
                rl_loadingmore.setVisibility(View.GONE);
                FidoData.getInstance().setCurrentPage(response.body().getMeta().getCurrentPage());
                if (FidoData.getInstance().getLastPage() == 0) {
                    FidoData.getInstance().setLastPage(response.body().getMeta().getLastPage());
                }

            }

            @Override
            public void onFailure(Call<Doctors> call, Throwable t) {
                //Toast.makeText(MainActivity.this, "faid", Toast.LENGTH_SHORT).show();
            }
        });
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
    public void onItemClick(int pos, Doctor doctor, CircleImageView sharedImageView) {
        Intent intent = new Intent(getActivity(), DetailDoctorActivity.class);
        intent.putExtra(DOCTOR_ITEM, doctor);
        intent.putExtra(DOCTOR_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(sharedImageView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(),
                sharedImageView,
                ViewCompat.getTransitionName(sharedImageView));

        startActivity(intent, options.toBundle());
        FidoData.getInstance().setCurrentDoctor(doctor);
    }


    @Override
    public void onSearchSubmit(String name, String special_id, String address_id) {
        listFakeDoctors.clear();
        loadMoreSuggesstion(name, special_id, address_id, "1");
        FidoData.getInstance().setSearch(name, special_id, address_id);

    }

    @Override
    public void onCloseSearch() {
        listFakeDoctors.clear();
        getData();

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
