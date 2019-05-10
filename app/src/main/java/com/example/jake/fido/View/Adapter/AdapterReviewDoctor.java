package com.example.jake.fido.View.Adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.devs.readmoreoption.ReadMoreOption;
import com.example.jake.fido.Objects.ReviewObject;
import com.example.jake.fido.R;
import com.example.jake.fido.Retrofit.ObjectRetrofit.Review;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterReviewDoctor extends RecyclerView.Adapter<AdapterReviewDoctor.ViewHolderReview> {
    private Context mContext;
    private Activity mActivity;
    private List<Review> listReview = new ArrayList<>();
    ReadMoreOption readMoreOption;

    @TargetApi(Build.VERSION_CODES.M)
    public AdapterReviewDoctor(List<Review> listReview, Context mContext, Activity mActivity){
        this.mActivity= mActivity;
        this.mContext = mContext;
        this.listReview = listReview;
        readMoreOption = new ReadMoreOption.Builder(mContext)
                .moreLabel("Đọc Thêm")
                .lessLabel("Ẩn đi")
                .moreLabelColor(mContext.getColor(R.color.colorPrimaryDark))
                .lessLabelColor(mContext.getColor(R.color.colorPrimaryDark))
                .labelUnderLine(true)
                .build();

    }
    @NonNull
    @Override
    public AdapterReviewDoctor.ViewHolderReview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.review_element,parent,false);
        return new ViewHolderReview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterReviewDoctor.ViewHolderReview holder, int position) {
            final Review review = listReview.get(position);

            readMoreOption.addReadMoreTo(holder.tv_content_review,review.getReview());
            if(review.getPatientAvatar()!=null && !"".equals(review.getPatientAvatar())){

                Glide.with(mContext).load(review.getPatientAvatar()).fitCenter().into(holder.cv_user);
            }
            holder.tv_nameuser.setText(review.getPatientName());
        holder.ratingBar.setStepSize(0.1f);
        holder.ratingBar.setRating(Float.parseFloat(review.getStar()));

    }

    @Override
    public int getItemCount() {
        return listReview.size();
    }
    class  ViewHolderReview extends RecyclerView.ViewHolder{
        CircleImageView cv_user;
        TextView tv_nameuser;
        RatingBar ratingBar;
        TextView tv_content_review;
        public ViewHolderReview(View itemView) {
            super(itemView);
            cv_user = itemView.findViewById(R.id.imv_user_review);
            tv_nameuser = itemView.findViewById(R.id.tv_name_user_review);
            ratingBar = itemView.findViewById(R.id.rating_review);
            tv_content_review = itemView.findViewById(R.id.tv_review_content);
        }

        public void addReview(Review review){
        listReview.add(review);
        notifyDataSetChanged();
        }
    }
}
