package com.example.jake.fido.Instance;

import com.example.jake.fido.Retrofit.ObjectRetrofit.Doctor;

import java.util.ArrayList;
import java.util.List;

public class FidoData {
    public static FidoData fidoData;
    private int currentPage;
    private int lastPage;
    private int typeShow=1;

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
