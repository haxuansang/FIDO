package com.example.jake.fido.View.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jake.fido.Instance.APIFido;
import com.example.jake.fido.Instance.FidoData;
import com.example.jake.fido.MainActivity;
import com.example.jake.fido.MultipleImagesSelector.ImagesSelectorActivity;
import com.example.jake.fido.MultipleImagesSelector.SelectorSettings;
import com.example.jake.fido.R;
import com.example.jake.fido.Retrofit.LoginRetrofit;
import com.example.jake.fido.Retrofit.ResetPassword;
import com.example.jake.fido.View.Adapter.AdapterSpinner;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

import static android.app.Activity.RESULT_OK;
import static android.support.constraint.Constraints.TAG;
import static com.example.jake.fido.Utils.Constants.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpdateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<String> mResults =new ArrayList<>();
    private static final int REQUEST_CODE_CHOOSE =1997 ;
    private View mainView;
    File file;

    @BindView(R.id.ln_update_doctor)
    LinearLayout ln_update_doctor;
    @BindView(R.id.ci_user_manage)
    CircleImageView ci_user;
    @BindView(R.id.edt_register_Email)
    EditText edt_email;
    @BindView(R.id.edt_register_Username)
    EditText edt_username;
    @BindView(R.id.edt_register_Password)
    EditText edt_password;
    @BindView(R.id.edt_register_PassworkVerify)
    EditText edt_password_verify;
    @BindView(R.id.edt_phone)
    EditText edt_phone;
    @BindView(R.id.edt_cmnd)
    EditText edt_cmnd;
    @BindView(R.id.edt_date_cmnd)
    EditText edt_cmnd_date;
    @BindView(R.id.edt_address_cmnd)
    EditText edt_cmnd_addres;
    @BindView(R.id.spinner_city_doctor)
    Spinner spinerr_city;
    @BindView(R.id.spinner_major_doctor)
    Spinner spierr_major;
    @BindView(R.id.spinner_submajor_doctor)
    Spinner spiner_submajor;
    @BindView(R.id.btn_address)
    Button btn_addres;
    @BindView(R.id.edt_chucvu)
    EditText edt_chucvu;
    @BindView(R.id.edt_description)
    EditText edt_description;
    @BindView(R.id.edt_address_working)
    EditText edt_address_working;
    @BindView(R.id.edt_expierence)
    EditText edt_expierence;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.radiobtn_gender_Male)
    RadioButton radioMale;
    @BindView(R.id.radiobtn_gender_Female)
    RadioButton radioFemale;
    ProgressDialog progressDialog;
    Callback<LoginRetrofit> callback= new Callback<LoginRetrofit>() {
        @Override
        public void onResponse(Call<LoginRetrofit> call, Response<LoginRetrofit> response) {
            if (response.isSuccessful()) {
                progressDialog.dismiss();
                if(response.body().getStatusCode()==200){
                    Toast.makeText(getContext(), "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                    FidoData.getInstance().getLoginRetrofit().setData(response.body().getData());
                    Glide.with(getContext()).load(response.body().getData().getAvatar()).into(MainActivity.cv_image_user);


                }

            }
        }

        @Override
        public void onFailure(Call<LoginRetrofit> call, Throwable t) {
            progressDialog.dismiss();
            Toast.makeText(getContext(), "Không cập nhật được!", Toast.LENGTH_SHORT).show();
        }
    };
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public UpdateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateFragment newInstance(String param1, String param2) {
        UpdateFragment fragment = new UpdateFragment();
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
        mainView = inflater.inflate(R.layout.fragment_update, container, false);
        ButterKnife.bind(this,mainView);
        ci_user.setOnClickListener(this);
        setData();
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    updateData();
            }
        });
        if(FidoData.getInstance().getLoginRetrofit().getData().getAvatar()==null)
            Glide.with(getContext()).load(R.drawable.fakedoctor).into(ci_user);
        return mainView;
    }

    private void updatePassWord() {

        final RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", edt_email.getText().toString())
                .addFormDataPart("password", edt_password.getText().toString())
                .addFormDataPart("resetPassword", edt_password_verify.getText().toString())
                .addFormDataPart("_method","PUT")
                .build();
        APIFido.getInstance().getSoService().resetpassword(requestBody).enqueue(new Callback<ResetPassword>() {
            @Override
            public void onResponse(Call<ResetPassword> call, Response<ResetPassword> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    if(response.body().getMessage().equals("Success"))
                    Toast.makeText(getContext(), "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(), "Cập nhật mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getContext(), "Cập nhật mật khẩu không thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResetPassword> call, Throwable t) {
                Toast.makeText(getContext(), "Cập nhật mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }

    private void updateData() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Đang cập nhật thông tin");
        progressDialog.setMessage("Vui lòng chờ...");
        progressDialog.show();

        MultipartBody.Part body = null;
        if(this.file!=null) {
            RequestBody requestFile =RequestBody.create(MediaType.parse("image/*"),this.file);
            body = MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);
        }


        RequestBody name =
                    RequestBody.create(
                            okhttp3.MultipartBody.FORM, edt_username.getText().toString());
        RequestBody  gender=
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, radioMale.isChecked()?"Male":"Female");
        RequestBody phone_number =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, edt_phone.getText().toString());
        RequestBody id_number = RequestBody.create(
                okhttp3.MultipartBody.FORM, edt_cmnd.getText().toString());
        RequestBody id_number_date = RequestBody.create(
                okhttp3.MultipartBody.FORM, edt_cmnd_date.getText().toString());
        RequestBody id_number_place = RequestBody.create(
                okhttp3.MultipartBody.FORM, edt_cmnd_addres.getText().toString());
        RequestBody address_id  = RequestBody.create(
                okhttp3.MultipartBody.FORM, String.valueOf(spinerr_city.getSelectedItemPosition()+1));
        RequestBody address_detail = RequestBody.create(
                okhttp3.MultipartBody.FORM,btn_addres.getText().toString());
        RequestBody major_id = RequestBody.create(
                okhttp3.MultipartBody.FORM, String.valueOf(spierr_major.getSelectedItemPosition()+2));
        RequestBody sub_major_id = RequestBody.create(
                okhttp3.MultipartBody.FORM, String.valueOf(spiner_submajor.getSelectedItemPosition()+2));
        RequestBody chucvu  = RequestBody.create(
                okhttp3.MultipartBody.FORM, edt_chucvu.getText().toString());
        RequestBody office = RequestBody.create(
                okhttp3.MultipartBody.FORM, edt_address_working.getText().toString());
        RequestBody description = RequestBody.create(
                okhttp3.MultipartBody.FORM, edt_description.getText().toString());
        RequestBody expierence =  RequestBody.create(
                okhttp3.MultipartBody.FORM, edt_expierence.getText().toString());
        RequestBody method =  RequestBody.create(
                okhttp3.MultipartBody.FORM, "put");
        
        if(FidoData.getInstance().getLoginRetrofit().getUsableType().equals("App\\Doctor")) {
            if (body != null)
                APIFido.getInstance().getSoService().updateDoctorFile(FidoData.getInstance().getLoginRetrofit().getData().getId().toString(), body, name, gender, phone_number, id_number,
                        id_number_date, id_number_place, address_id, address_detail, major_id, sub_major_id, chucvu, office, description, expierence, method).enqueue(callback);



            else {
                APIFido.getInstance().getSoService().updateDoctor(FidoData.getInstance().getLoginRetrofit().getData().getId().toString(), name, gender, phone_number, id_number,
                        id_number_date, id_number_place, address_id, address_detail, major_id, sub_major_id, chucvu, office, description, expierence, method).enqueue(callback);

            }
        }
        else {
            if (body != null)
                APIFido.getInstance().getSoService().updatePatientsFile(FidoData.getInstance().getLoginRetrofit().getData().getId().toString(), body, name, gender, phone_number, id_number,
                        id_number_date, id_number_place, address_id,method).enqueue(callback);
            else
                APIFido.getInstance().getSoService().updatePatients(FidoData.getInstance().getLoginRetrofit().getData().getId().toString(), name, gender, phone_number, id_number,
                        id_number_date, id_number_place, address_id,method).enqueue(callback);
        }
        if(!edt_password_verify.getText().toString().equals("") && !edt_password.getText().toString().equals("")){
            updatePassWord();
        }

    }

    public boolean isEmptyString(String a){
        if (a.equals(""))
            return true;
        else return false;
    }

    private void createSpinnerCity() {
        String[] listCity =getResources().getStringArray(R.array.listcity);
        ArrayList<String> listOfCity = new ArrayList<>();
        for (int i=0;i<listCity.length;i++)
        {
            listOfCity.add(listCity[i]);
        }
        AdapterSpinner adapterSpinner  = new AdapterSpinner(getContext(),listOfCity);
        spinerr_city.setAdapter(adapterSpinner);
        if(FidoData.getInstance().getLoginRetrofit().getData().getAddressId()!=null)
        spinerr_city.setSelection(FidoData.getInstance().getLoginRetrofit().getData().getAddressId()-1);


    }
    private void createSpinnerMajor() {
        String[] listMajor =getResources().getStringArray(R.array.listmajor);
        ArrayList<String> listOfMajor = new ArrayList<>();
        for (int i=0;i<listMajor.length;i++)
        {
            listOfMajor.add(listMajor[i]);
        }
        AdapterSpinner adapterSpinner  = new AdapterSpinner(getContext(),listOfMajor);
        spierr_major.setAdapter(adapterSpinner);
        spierr_major.setSelection(Integer.valueOf(FidoData.getInstance().getLoginRetrofit().getData().getSpecialistId())-2);


    }
    private void createSpinnerSubMajor() {
        String[] listMajor =getResources().getStringArray(R.array.listmajor);
        ArrayList<String> listOfMajor = new ArrayList<>();
        for (int i=0;i<listMajor.length;i++)
        {
            listOfMajor.add(listMajor[i]);
        }
        AdapterSpinner adapterSpinner  = new AdapterSpinner(getContext(),listOfMajor);
        spiner_submajor.setAdapter(adapterSpinner);
        if(FidoData.getInstance().getLoginRetrofit().getData().getSubSpecialistId()!=null){
            spiner_submajor.setSelection(Integer.valueOf(FidoData.getInstance().getLoginRetrofit().getData().getSubSpecialistId())-2);
        }

    }


    private void setData() {
        if(FidoData.getInstance().getLoginRetrofit().getUsableType().equals("App\\Patient")){
            ln_update_doctor.setVisibility(View.GONE);
            createSpinnerCity();
        }
        else{
            createSpinnerCity();
            createSpinnerMajor();
            createSpinnerSubMajor();
        }

        Glide.with(getContext()).load(FidoData.getInstance().getLoginRetrofit().getData().getAvatar()).fitCenter().into(ci_user);
        edt_email.setText(FidoData.getInstance().getLoginRetrofit().getData().getEmail());
        edt_username.setText(FidoData.getInstance().getLoginRetrofit().getData().getName());
        edt_cmnd.setText(FidoData.getInstance().getLoginRetrofit().getData().getIdNumber());
        edt_cmnd_addres.setText(FidoData.getInstance().getLoginRetrofit().getData().getIdNumberPlace());
        edt_cmnd_date.setText(FidoData.getInstance().getLoginRetrofit().getData().getIdNumberDate());
        if(FidoData.getInstance().getLoginRetrofit().getData().getAddressDetails()!=null && FidoData.getInstance().getLoginRetrofit().getData().getAddressDetails().equals(""))
            btn_addres.setText(FidoData.getInstance().getLoginRetrofit().getData().getAddressDetails());
        else btn_addres.setText("Phường, Xã, Quận, Huyện");
        edt_address_working.setText(FidoData.getInstance().getLoginRetrofit().getData().getOffice());
        edt_chucvu.setText(FidoData.getInstance().getLoginRetrofit().getData().getTitle());
        edt_description.setText(FidoData.getInstance().getLoginRetrofit().getData().getDescription());
        edt_phone.setText(FidoData.getInstance().getLoginRetrofit().getData().getPhoneNumber());
        edt_expierence.setText(FidoData.getInstance().getLoginRetrofit().getData().getExperience());
        if(FidoData.getInstance().getLoginRetrofit().getData().getGender().equals("Male")){
            radioMale.setChecked(true);
        }
        else {
            radioFemale.setChecked(true);
        }


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
       /* if (context instanceof OnFragmentInteractionListener) {
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ci_user_manage:
                loadImageFromLibrary();
        }
    }

    private void loadImageFromLibrary() {
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



    private void getImages() {


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
             mResults = new ArrayList<>();
             mResults = data.getStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS);
             this.file = new File(mResults.get(0));
             Glide.with(getContext()).load(mResults.get(0)).into(ci_user);
        }
    }
}
