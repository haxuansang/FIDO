package com.example.jake.fido.View.Fragment;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jake.fido.Instance.APIFido;
import com.example.jake.fido.Instance.FidoData;
import com.example.jake.fido.MultipleImagesSelector.ImagesSelectorActivity;
import com.example.jake.fido.MultipleImagesSelector.SelectorSettings;
import com.example.jake.fido.R;
import com.example.jake.fido.Retrofit.ChungChi;
import com.example.jake.fido.Retrofit.ChungChiRetrofit;
import com.example.jake.fido.Retrofit.Data;
import com.example.jake.fido.View.Adapter.AdapterChungChi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.support.constraint.Constraints.TAG;
import static com.example.jake.fido.Utils.Constants.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChungChiFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChungChiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChungChiFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<String> mResults =new ArrayList<>();
    private List<Data> list = new ArrayList<>();
    private AdapterChungChi adapterChungChi;
    private static final int REQUEST_CODE_CHOOSE =1997 ;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.cv_addpicture)
    CardView btn_addpicture;
    @BindView(R.id.rv_chunghci)
    RecyclerView rv_chungchi;
    View mainView;
    ProgressDialog progressDialog;
    public ChungChiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChungChiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChungChiFragment newInstance(String param1, String param2) {
        ChungChiFragment fragment = new ChungChiFragment();
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
        list = FidoData.getInstance().getDataList();
        if(list!=null)
           adapterChungChi = new AdapterChungChi(list,getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView=inflater.inflate(R.layout.fragment_chung_chi, container, false);
        ButterKnife.bind(this,mainView);
        btn_addpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getChungChi();
            }
        });
        rv_chungchi.setLayoutManager( new LinearLayoutManager(getContext()));
        rv_chungchi.setAdapter(adapterChungChi);

        return mainView;
    }

    private void getChungChi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED || getActivity().checkSelfPermission(Manifest.permission.WRITE_SETTINGS)
                    != PackageManager.PERMISSION_GRANTED ) {

                // Should we show an explanation?
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // Explain to the user why we need to read the contacts
                }

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_SETTINGS},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                // app-defined int constant that should be quite unique


            }
        }

        Intent intent = new Intent(getActivity(), ImagesSelectorActivity.class);
        // max number of images to be selected
        intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 1);
        // min size of image which will be shown; to filter tiny images (mainly icons)
        intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 100000);
        // show camera or not
        intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
        // pass current selected images as the initial value
        intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, mResults);
        // start the selector
        startActivityForResult(intent, REQUEST_CODE_CHOOSE);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            Log.d(TAG, "onActivityResult: aaaaaaa");
            mResults = new ArrayList<>();
            mResults = data.getStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS);
            // DataGalery.getInstance().putListEncodeImages(mResults);
            createDialog(mResults.get(0));
        }
    }

    private void createDialog(String uri) {

        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_chungchi);
        ImageView imv_chungchi = (ImageView) dialog.findViewById(R.id.iv_chungchi);
        EditText edt_title = (EditText) dialog.findViewById(R.id.edt_chungchiname);
        EditText edt_description= (EditText) dialog.findViewById(R.id.edt_description);
        Button btn_submit = (Button) dialog.findViewById(R.id.btn_submit);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(!checkEmpty(uri) && !checkEmpty(edt_title.getText().toString()) && !checkEmpty(edt_description.getText().toString())) {
                ChungChi chungChi = new ChungChi(uri, edt_title.getText().toString(), edt_description.getText().toString());
                upChungChi(chungChi,dialog);
            }

            else {
                Toast.makeText(getContext(), "Xin mời nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }

            }
        });
        Glide.with(getContext()).load(uri).fitCenter().into(imv_chungchi);
        dialog.show();

    }

    private void upChungChi(ChungChi chungChi,Dialog dialog) {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Đang cập nhật thông tin");
        progressDialog.setMessage("Vui lòng chờ...");
        progressDialog.show();
        File file = new File(chungChi.getUri());
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/*"),
                        file
                );
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, chungChi.getDescription());
        RequestBody name =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, chungChi.getTitle());



        /*RequestBody name = RequestBody.create(MediaType.parse("text"),)*/
        APIFido.getInstance().getSoService().upChungChi(FidoData.getInstance().getLoginRetrofit().getData().getId().toString(),body,name,description).enqueue(new Callback<ChungChiRetrofit>() {
            @Override
            public void onResponse(Call<ChungChiRetrofit> call, Response<ChungChiRetrofit> response) {
                Log.e(TAG, "onResponse: " + response.code() );
                dialog.dismiss();
                if (response.isSuccessful()){
                    if (response.body().getStatusCode()==201){
                        Toast.makeText(getContext(), "Tải lên thành công! Chờ xác nhận của admin.", Toast.LENGTH_SHORT).show();
                        list.add(response.body().getData());
                        adapterChungChi.notifyDataSetChanged();
                    }
                    else  {
                        Toast.makeText(getContext(), "Tải lên thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getContext(), "Có lỗi đường truyền!", Toast.LENGTH_SHORT).show();
                }
                }


            @Override
            public void onFailure(Call<ChungChiRetrofit> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Tải lên thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkEmpty(String a){
        if(a.equals(""))
            return true;
        return false;
    }
}
