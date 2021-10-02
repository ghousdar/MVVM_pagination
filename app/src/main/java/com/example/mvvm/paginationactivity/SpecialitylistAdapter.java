package com.example.mvvm.paginationactivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mvvm.R;

import java.util.List;

public class SpecialitylistAdapter extends RecyclerView.Adapter<SpecialitylistAdapter.ViewHolder> {
    Context context;
    List<Speciality> specialityList;

    int lastposition = -1;

    public SpecialitylistAdapter(Context context, List<Speciality> specialityList) {
        this.context = context;
        this.specialityList = specialityList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.speciallist, parent, false);
        return new SpecialitylistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (holder.getAdapterPosition() > lastposition) {


            Glide.with(context)
                    .load(specialityList.get(position).getImage())

                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(holder.imagspeciality);


            holder.specialityname.setText(specialityList.get(position).getName());

            holder.specialitycard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, ""+specialityList.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
            });

            lastposition = holder.getAdapterPosition();

        }

    }

    @Override
    public int getItemCount() {
        return specialityList.size();
    }


    public void addAll(List<Speciality> asList) {
        int lastIndex = specialityList.size();
        specialityList.addAll(asList);

        notifyItemRangeInserted(lastIndex, asList.size());

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imagspeciality;
        TextView specialityname;
        CardView specialitycard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagspeciality = itemView.findViewById(R.id.imagspeciality);

            specialityname = itemView.findViewById(R.id.specialityname);

            specialitycard = itemView.findViewById(R.id.specialitycard);


        }
    }


}