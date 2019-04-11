package com.example.jake.fido.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jake.fido.Objects.DoctorObjects;
import com.example.jake.fido.R;
import com.example.jake.fido.Utils.ItemClickListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterDoctors extends RecyclerView.Adapter<AdapterDoctors.ViewHolderDoctors>{
    ArrayList<DoctorObjects> listDoctors;
    Activity mActivity;
    Context mContext;
    ItemClickListener itemClickListener;
    public AdapterDoctors(Activity mActivity, Context mContext, ArrayList<DoctorObjects> listDoctors)
    {
        this.mActivity = mActivity;
        this.mContext=mContext;
        this.listDoctors=listDoctors;

    }
    public void registerItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolderDoctors onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.doctor_elements,parent,false);
        return new ViewHolderDoctors(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDoctors holder, int position) {
        final DoctorObjects doctorObjects = listDoctors.get(position);
        Picasso.with(mContext).load(doctorObjects.getImageLink()).fit().into(holder.doctorImage);
        holder.tvDoctorName.setText(doctorObjects.getName());
        holder.tvMajor.setText("Chuyên khoa:"+doctorObjects.getMajor());
        holder.tvAddress.setText(doctorObjects.getAddress());
        holder.tvDescription.setText(doctorObjects.getDescription());
        holder.ratingBar.setNumStars((int)doctorObjects.getEvaluation());
        holder.rlDoctorElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(doctorObjects);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listDoctors.size();
    }
    class ViewHolderDoctors extends RecyclerView.ViewHolder{
        CircleImageView doctorImage;
        TextView tvDoctorName,tvMajor,tvDescription,tvAddress;
        RatingBar ratingBar;
        RelativeLayout rlDoctorElement;
        public ViewHolderDoctors(View itemView) {
            super(itemView);
            doctorImage = (CircleImageView) itemView.findViewById(R.id.circleview_doctor);
            tvDoctorName = (TextView)itemView.findViewById(R.id.tv_namedoctor);
            tvMajor = (TextView)itemView.findViewById(R.id.tv_major_doctor);
            tvDescription = (TextView)itemView.findViewById(R.id.tv_evaluate_doctor);
            tvAddress = (TextView)itemView.findViewById(R.id.tv_addressofdoctor);
            ratingBar = (RatingBar)itemView.findViewById(R.id.rating_doctor);
            rlDoctorElement = (RelativeLayout)itemView.findViewById(R.id.rl_doctor_element);

        }
    }
}
