package com.example.jake.fido;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.jake.fido.Objects.DoctorObjects;
import com.example.jake.fido.Objects.ReviewObject;
import com.example.jake.fido.Utils.Constants;
import com.example.jake.fido.Utils.CreateDialogReview;
import com.example.jake.fido.Utils.ReviewOnClickListener;
import com.example.jake.fido.View.Adapter.PagerAdapterDetail;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailDoctorActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager pager;
    private TabLayout tabLayout;
    private CircleImageView imvDoctor;
    private TextView  tvDoctor;
    private FloatingActionButton fb_review;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_doctor);
        addControl();
        getDoctorDataTransition();
    }

    private void getDoctorDataTransition() {
        imvDoctor = (CircleImageView)findViewById(R.id.circleview_doctor);
        tvDoctor  = (TextView)findViewById(R.id.tv_namedoctor);
        fb_review = (FloatingActionButton)findViewById(R.id.fb_review);
        fb_review.setOnClickListener(this);
        supportPostponeEnterTransition();
        Bundle extras = getIntent().getExtras();
        DoctorObjects doctorObjects = extras.getParcelable(Constants.DOCTOR_ITEM);
        tvDoctor.setText(doctorObjects.getName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName = extras.getString(Constants.DOCTOR_IMAGE_TRANSITION_NAME);
            imvDoctor.setTransitionName(imageTransitionName);
        }
        Picasso.with(this).load(doctorObjects.getImageLink()).noFade().into(imvDoctor, new Callback() {
            @Override
            public void onSuccess() {
                supportStartPostponedEnterTransition();
            }

            @Override
            public void onError() {
                supportStartPostponedEnterTransition();
            }
        });
    }

    private void addControl() {
        pager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        FragmentManager manager = getSupportFragmentManager();
        PagerAdapterDetail adapter = new PagerAdapterDetail(manager);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);//deprecated
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0)
                    fb_review.setVisibility(View.GONE);
                else
                    fb_review.setVisibility(View.VISIBLE);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        View root = tabLayout.getChildAt(0);
        /*if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.color_line));
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }*/


    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.fb_review) {
            CreateDialogReview.getInstance().makeDialogReview(this, new ReviewOnClickListener() {
                @Override
                public void onReviewClickListener(ReviewObject reviewObject) {
                    Log.e("sangha", "onReviewClickListener: "+reviewObject.getContentReview() + reviewObject.getContentReview() );
                }
            });
        }
    }
}
