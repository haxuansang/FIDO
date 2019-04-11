package com.example.jake.fido.View.Adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.jake.fido.View.Fragment.InformationDoctorFragment;
import com.example.jake.fido.View.Fragment.ReviewDoctorFragment;


public class PagerAdapterDetail extends FragmentStatePagerAdapter {


    public PagerAdapterDetail(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag = new InformationDoctorFragment();
                break;
            case 1:
                frag = new ReviewDoctorFragment();
                break;

        }
        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Thông tin";
                break;
            case 1:
                title = "Đánh giá";
                break;

        }
        return title;
    }
}
