package com.example.jake.fido;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jake.fido.Instance.APIFido;
import com.example.jake.fido.Instance.FidoData;
import com.example.jake.fido.Retrofit.LoginRetrofit;
import com.example.jake.fido.Retrofit.ObjectRetrofit.City;
import com.example.jake.fido.Retrofit.ObjectRetrofit.Doctors;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final int LOCATION_REQUEST_CODE = 1001;
    @BindView(R.id.edt_Username)
    EditText edt_username;
    @BindView(R.id.edt_Password)
    EditText edt_password;
    Button btnRegister;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getCity();
        ButterKnife.bind(this);
        createView();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
            return;
        }


    }

    private void getCity() {
        APIFido.getInstance().getSoService().getCity().enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                List<City> list = response.body();
                for (City city : list
                ) {
                    System.out.println("<item>" + city.getName() + "</item>\n");
                }
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {

            }
        });
    }

    private void createView() {
        btnRegister = (Button) findViewById(R.id.btn_Register);
        btnLogin = (Button) findViewById(R.id.btn_Login);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                LoginActivity.this.startActivity(intent);

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_username.getText() != null && edt_password.getText() != null) {
                    if (!"".equals(edt_username.getText().toString()) && !"".equals(edt_password.getText().toString())) {
                        final RequestBody requestBody = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("email", edt_username.getText().toString())
                                .addFormDataPart("password", edt_password.getText().toString())
                                .build();
                        APIFido.getInstance().getSoService().login(requestBody).enqueue(new Callback<LoginRetrofit>() {
                            @Override
                            public void onResponse(Call<LoginRetrofit> call, Response<LoginRetrofit> response) {
                                if (response.body() != null) {
                                    if (response.body().getStatusCode()==200) {
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        LoginActivity.this.startActivity(intent);
                                        FidoData.getInstance().setLoginRetrofit(response.body());
                                        finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Tài khoản không chính xác", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "Tài khoản không chính xác", Toast.LENGTH_SHORT).show();
                                }
                            }


                            @Override
                            public void onFailure(Call<LoginRetrofit> call, Throwable t) {

                            }
                        });
                    } else {
                        Toast.makeText(LoginActivity.this, "Xin mời nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
