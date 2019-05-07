package com.example.jake.fido.Instance;

import com.example.jake.fido.Retrofit.LoginRetrofit;
import com.example.jake.fido.Retrofit.ObjectRetrofit.Doctor;

import java.util.ArrayList;
import java.util.List;

public class FidoData {
    public static FidoData fidoData;
    private int currentPage;
    private int lastPage;
    private int typeShow=1;
    private String search="";
    private String special_id= "";
    private String address_id = "";
    private LoginRetrofit loginRetrofit;

    public LoginRetrofit getLoginRetrofit() {
        return loginRetrofit;
    }

    public void setLoginRetrofit(LoginRetrofit loginRetrofit) {
        this.loginRetrofit = loginRetrofit;
    }

    public void setSearch(String name, String si, String ai)
    {
        this.search = name;
        this.special_id = si;
        this.address_id = ai;
    }
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSpecial_id() {
        return special_id;
    }

    public void setSpecial_id(String special_id) {
        this.special_id = special_id;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public int getTypeShow() {
        return typeShow;
    }

    public void setTypeShow(int typeShow) {
        this.typeShow = typeShow;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    private List<Doctor> listDoctors = new ArrayList<>();
    private Doctor currentDoctor;

    public static FidoData getInstance() {
        if (fidoData == null) {
            fidoData = new FidoData();
        }
        return fidoData;
    }

    public List<Doctor> getListDoctors() {
        return listDoctors;
    }

    public void setListDoctors(List<Doctor> listDoctors) {
        this.listDoctors = listDoctors;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Doctor getCurrentDoctor() {
        return currentDoctor;
    }

    public void setCurrentDoctor(Doctor currentDoctor) {
        this.currentDoctor = currentDoctor;
    }

    public void addDoctor(Doctor doctor) {
        listDoctors.add(doctor);
    }
    public void addListDoctor(List<Doctor> doctors){
        for (Doctor doctor: doctors) {
            listDoctors.add(doctor);
        }
    }
    public void updatePage(int page) {
        this.currentPage = page;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public Boolean isLoadMore(){
        if(currentPage<lastPage) {
            return true;
        }
        return false;
    }
}
