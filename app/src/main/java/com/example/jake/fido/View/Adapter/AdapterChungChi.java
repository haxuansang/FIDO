package com.example.jake.fido.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jake.fido.Instance.APIFido;
import com.example.jake.fido.Instance.FidoData;
import com.example.jake.fido.R;
import com.example.jake.fido.Retrofit.ChungChiRetrofit;
import com.example.jake.fido.Retrofit.Data;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterChungChi extends RecyclerView.Adapter<AdapterChungChi.ViewHolder> {
    List<Data> listChungChi = new ArrayList<>();
    Context context;

    public AdapterChungChi(List<Data> listChungChi, Context context) {
        this.listChungChi = listChungChi;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterChungChi.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mainView = LayoutInflater.from(context).inflate(R.layout.viewholder_chungchi,parent,false);
        return new ViewHolder(mainView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChungChi.ViewHolder holder, int position) {
        Data chungChi = listChungChi.get(position);
        Glide.with(context).load(chungChi.getImage()).fitCenter().into(holder.imv_chungchi);
        holder.tv_title.setText(chungChi.getName());
        holder.tv_description.setText(chungChi.getDescription());
        holder.iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIFido.getInstance().getSoService().deleteChungChi(FidoData.getInstance().getLoginRetrofit().getData().getId().toString(),chungChi.getId().toString()).enqueue(new Callback<ChungChiRetrofit>() {
                    @Override
                    public void onResponse(Call<ChungChiRetrofit> call, Response<ChungChiRetrofit> response) {
                        if(response.isSuccessful()){
                            if(response.body().getStatusCode()==204) {
                                listChungChi.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Xóa chứng chỉ thành công", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ChungChiRetrofit> call, Throwable t) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return listChungChi.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_chungchi)
        ImageView imv_chungchi;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_description)
        TextView tv_description;
        @BindView(R.id.imv_close)
        ImageView iv_close;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
