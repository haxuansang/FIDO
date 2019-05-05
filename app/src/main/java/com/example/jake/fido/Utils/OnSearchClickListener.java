package com.example.jake.fido.Utils;

import com.example.jake.fido.Retrofit.ObjectRetrofit.Doctor;

import java.util.List;

public interface OnSearchClickListener {
    void onSearchSubmit(List<Doctor> listDoctos);
    void onCloseSearch();
}
