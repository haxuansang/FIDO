package com.example.jake.fido;

import android.app.AlertDialog;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jake.fido.Instance.APIFido;
import com.example.jake.fido.Retrofit.LoginRetrofit;
import com.example.jake.fido.Retrofit.RegisterRetrofit;
import com.example.jake.fido.View.Adapter.AdapterSpinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @BindView(R.id.spinner_city_doctor)
    Spinner spinnerCity;
    @BindView(R.id.spinner_major_doctor)
    Spinner spinnerMajor;
    LinearLayout linearDoctor;
    @BindView(R.id.already_user)
    Button btn_already_user;
    @BindView(R.id.btn_RegisterSubmit)
    Button btn_register;
    @BindView(R.id.edt_register_email)
    EditText edt_email;
    @BindView(R.id.edt_register_username)
    EditText edt_username;
    @BindView(R.id.edt_register_Password)
    EditText edt_password;
    @BindView(R.id.edt_register_PassworkVerify)
    EditText edt_password_verify;
    @BindView(R.id.radiobtn_gender_Male)
    RadioButton radioButtonMale;
    private int type=0;
    Callback<LoginRetrofit> callback = new Callback<LoginRetrofit>() {
        @Override
        public void onResponse(Call<LoginRetrofit> call, Response<LoginRetrofit> response) {
            if(response.body()!=null) {
                if (response.body().getStatusCode()==200) {
                    Toast.makeText(SignUpActivity.this, "Đăng ký thành công mời bạn đăng nhập!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else Toast.makeText(SignUpActivity.this, "Đăng ký thất bại! Vui lòng kiểm tra lại.", Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(SignUpActivity.this, "Đăng ký thất bại! Vui lòng kiểm tra lại.", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onFailure(Call<LoginRetrofit> call, Throwable t) {
            Toast.makeText(SignUpActivity.this, "Đăng ký thất bại! Vui lòng kiểm tra lại.", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        linearDoctor = (LinearLayout)findViewById(R.id.linear_signup_doctor);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        createSpinnerCity();
        createSpinnerMajor();
        btn_already_user.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    private void createListCity() {

    }
    private void createSpinnerCity() {
        String[] listCity =getResources().getStringArray(R.array.listcity);
        ArrayList<String> listOfCity = new ArrayList<>();
        for (int i=0;i<listCity.length;i++)
        {
            listOfCity.add(listCity[i]);
        }
        AdapterSpinner adapterSpinner  = new AdapterSpinner(getBaseContext(),listOfCity);
        spinnerCity.setAdapter(adapterSpinner);

    }
    private void createSpinnerMajor() {
        String[] listMajor =getResources().getStringArray(R.array.listmajor);
        ArrayList<String> listOfMajor = new ArrayList<>();
        for (int i=0;i<listMajor.length;i++)
        {
            listOfMajor.add(listMajor[i]);
        }
        AdapterSpinner adapterSpinner  = new AdapterSpinner(getBaseContext(),listOfMajor);
        spinnerMajor.setAdapter(adapterSpinner);

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.navigation_user:
                type=0;
                linearDoctor.setVisibility(View.GONE);
                break;
            case R.id.navigation_doctor:
                type=1;
                linearDoctor.setVisibility(View.VISIBLE);
                break;



        }
        return true;
    }

    private void register() {
        switch (type){
            case 0:
                if(!checkEmpty(edt_email.getText().toString()) && !checkEmpty(edt_username.getText().toString()) && !checkEmpty(edt_password.getText().toString()) &&
                !checkEmpty(edt_password_verify.getText().toString())){
                    RegisterRetrofit registerRetrofit = new RegisterRetrofit(edt_email.getText().toString(),edt_password.getText().toString(),edt_username.getText().toString(),"App\\Patient",radioButtonMale.isChecked()?"Male":"Female",null,null);
                    APIFido.getInstance().getSoService().register(registerRetrofit).enqueue(callback);
                }
                else Toast.makeText(this, "Xin mời nhập đầy đủ thông tin để đăng ký!", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                if(!checkEmpty(edt_email.getText().toString()) && !checkEmpty(edt_username.getText().toString()) && !checkEmpty(edt_password.getText().toString()) &&
                        !checkEmpty(edt_password_verify.getText().toString())){
                    RegisterRetrofit registerRetrofit = new RegisterRetrofit(edt_email.getText().toString(),edt_password.getText().toString(),edt_username.getText().toString(),"App\\Doctor",radioButtonMale.isChecked()?"Male":"Female",
                            spinnerCity.getSelectedItemPosition()+1,spinnerMajor.getSelectedItemPosition()+2);
                    APIFido.getInstance().getSoService().register(registerRetrofit).enqueue(callback);
                }
                else Toast.makeText(this, "Xin mời nhập đầy đủ thông tin để đăng ký!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private boolean checkEmpty(String a){
        if ("".equals(a))
            return true;
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.already_user:
                finish();
                break;
            case R.id.btn_RegisterSubmit:
                if(edt_password.getText().toString().equals(edt_password_verify.getText().toString()))
                register();
                else Toast.makeText(this, "Bạn phải nhập lại mật khẩu trùng với nhau!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
