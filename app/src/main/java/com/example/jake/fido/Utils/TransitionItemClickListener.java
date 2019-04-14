package com.example.jake.fido.Utils;

import android.widget.ImageView;

import com.example.jake.fido.Objects.DoctorObjects;

import de.hdodenhof.circleimageview.CircleImageView;

public interface TransitionItemClickListener {
    void onItemClick(int pos, DoctorObjects doctorObjects, CircleImageView sharedImageView);
}
