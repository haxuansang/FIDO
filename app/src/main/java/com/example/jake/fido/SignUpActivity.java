package com.example.jake.fido;

import android.app.AlertDialog;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jake.fido.View.Adapter.AdapterSpinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private TextView mTextMessage;
    private Spinner spinnerCity,spinnerMajor;
    LinearLayout linearDoctor;
    @BindView(R.id.already_user)
    Button btn_already_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        spinnerCity=(Spinner)findViewById(R.id.spinner_city_doctor);
        spinnerMajor=(Spinner)findViewById(R.id.spinner_major_doctor);
        mTextMessage = (TextView) findViewById(R.id.message);
        linearDoctor = (LinearLayout)findViewById(R.id.linear_signup_doctor);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        createSpinnerCity();
        createSpinnerMajor();
        btn_already_user.setOnClickListener(this);
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

                linearDoctor.setVisibility(View.GONE);
                break;
            case R.id.navigation_doctor:

                linearDoctor.setVisibility(View.VISIBLE);
                break;


        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.already_user:
                finish();
                break;

        }
    }
}
