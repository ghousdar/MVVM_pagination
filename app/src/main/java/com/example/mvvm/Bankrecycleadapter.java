package com.example.mvvm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mvvm.retrofitclass.Bankpojo;

import java.util.List;

public class Bankrecycleadapter extends RecyclerView.Adapter<Bankrecycleadapter.ViewHolder> {
    List<Bankpojo> userList;
    Context context;



    public Bankrecycleadapter(Context context, List<Bankpojo> userList) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bankrow, parent, false);
        return new Bankrecycleadapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.bankname.setText(userList.get(position).getName());

        Glide.with(context)
                .load( "https://admin.greenprofile.me/assets/uploads/bank/" + userList.get(position).getImage())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(holder.bankicon);






    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView bankicon;
        //  CircleImageView removebank;
        TextView bankname;
        CardView clickbankcard;
        RelativeLayout removebank;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bankicon = itemView.findViewById(R.id.bankicon);
            bankname = itemView.findViewById(R.id.bankname);



        }
    }


}
