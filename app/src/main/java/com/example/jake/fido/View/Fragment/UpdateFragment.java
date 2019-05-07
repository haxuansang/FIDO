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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jake.fido.MultipleImagesSelector.ImagesSelectorActivity;
import com.example.jake.fido.MultipleImagesSelector.SelectorSettings;
import com.example.jake.fido.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

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
    private View mainView;
    @BindView(R.id.btn_chungchi)
    Button btn_addpicture;
    private ArrayList<String> mResults =new ArrayList<>();
    private static final int REQUEST_CODE_CHOOSE =1997 ;
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
    @BindView(R.id.edt_address)
    EditText edt_addres;
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
        btn_addpicture.setOnClickListener(this);
        ci_user.setOnClickListener(this);
        return mainView;
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
            case R.id.btn_chungchi:
                getImages();
                break;
            case R.id.ci_user_manage:
                loadImageFromLibrary();
        }
    }

    private void loadImageFromLibrary() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(pickIntent, "Select Picture"), 7171);
    }



    private void getImages() {
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
        intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 5);
        // min size of image which will be shown; to filter tiny images (mainly icons)
        intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 100000);
        // show camera or not
        intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
        // pass current selected images as the initial value
        intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, mResults);
        // start the selector
        startActivityForResult(intent, REQUEST_CODE_CHOOSE);

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
            Toast.makeText(getContext(), "" + mResults.size(), Toast.LENGTH_SHORT).show();

          /*  if(adapterAdd==null)
            {
                Log.d(TAG, "onActivityResult: aaaaaaa11111");
                adapterAdd = new AdapterAdd(mResults,getContext(),getActivity());
                rv_addpicture.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                rv_addpicture.setAdapter(adapterAdd);
            }
            else {
                adapterAdd.updateNewList(mResults);
                Log.d(TAG, "onActivityResult: aaaaaaa22222");

            }*/
        } else if (requestCode == 7171) {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Đăng ảnh");
            progressDialog.setMessage("Vui lòng chờ...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            final Uri uri = data.getData();
            if (uri != null) {
                try {

                    final Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                    ci_user.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
