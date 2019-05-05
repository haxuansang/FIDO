package com.example.jake.fido.Utils;

import android.widget.ImageView;

import com.example.jake.fido.Objects.DoctorObjects;
import com.example.jake.fido.Retrofit.ObjectRetrofit.Doctor;

import de.hdodenhof.circleimageview.CircleImageView;

public interface TransitionItemClickListener {
    void onItemClick(int pos, Doctor doctor, CircleImageView sharedImageView);
}
