package com.example.jake.fido;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.media.Rating;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.jake.fido.Instance.FidoData;
import com.example.jake.fido.Objects.DoctorObjects;
import com.example.jake.fido.Objects.ReviewObject;
import com.example.jake.fido.Retrofit.ObjectRetrofit.Doctor;
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
    private TextView  tvDoctor,tvSpecialist,tvReviewLikes;
    private FloatingActionButton fb_review;
    private RatingBar ratingBar;



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
        tvSpecialist = (TextView)findViewById(R.id.tv_major_doctor);
        tvReviewLikes = (TextView) findViewById(R.id.tv_evaluate_doctor);
        ratingBar = (RatingBar) findViewById(R.id.rating_doctor);
        fb_review.setOnClickListener(this);
        supportPostponeEnterTransition();
        Bundle extras = getIntent().getExtras();
        Doctor doctor = extras.getParcelable(Constants.DOCTOR_ITEM);
        tvDoctor.setText(doctor.getName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName = extras.getString(Constants.DOCTOR_IMAGE_TRANSITION_NAME);
            imvDoctor.setTransitionName(imageTransitionName);
        }
        Picasso.with(this).load(doctor.getAvatar()).noFade().into(imvDoctor, new Callback() {
            @Override
            public void onSuccess() {
                supportStartPostponedEnterTransition();
            }

            @Override
            public void onError() {
                supportStartPostponedEnterTransition();
            }
        });
        tvSpecialist.setText("Chuyên khoa: "+doctor.getSpecialistName());
        if(doctor.getRating()!=null) {
            ratingBar.setStepSize(0.1f);
            ratingBar.setRating(Float.parseFloat(doctor.getRating()));
        }
        tvReviewLikes.setText("Đánh giá " +doctor.getRating() + " sao dựa trên "+ doctor.getLikes()+" lượt bình chọn");

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
