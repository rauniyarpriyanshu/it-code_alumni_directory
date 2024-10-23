package com.pr.alumni_directory.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.pr.alumni_directory.R;
import com.pr.alumni_directory.activity.DataView;
import com.pr.alumni_directory.data.AlumniData;
import com.pr.alumni_directory.data.AlumniSubData;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyRecyclerView extends RecyclerView.Adapter<MyViewHolder> {
    private List<AlumniData> alumniData;
    private List<AlumniData> myList;
    private boolean isAdmin = false;

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }


    private Context mContext;

    public MyRecyclerView(Context context, ArrayList<AlumniData> alumniData, ArrayList<AlumniSubData> alumniSubData) {
        mContext = context;
        this.alumniData = alumniData;

    }


    public MyRecyclerView(Context context, ArrayList<AlumniData> list) {
        mContext = context;
        myList = list;
    }

    public void setFilteredList(List<AlumniData> filteredList) {
        myList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alumi_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.getPersonName().setText(myList.get(holder.getAdapterPosition()).getName());
        holder.getPersonRole().setText(myList.get(holder.getAdapterPosition()).getJobTitle());
        holder.getPersonJob().setText(myList.get(holder.getAdapterPosition()).getSubData().getNameOfBusiness());
        holder.getPersonPhone().setText(myList.get(holder.getAdapterPosition()).getSubData().getBusinessPhone());
        holder.getPersonEmail().setText(myList.get(holder.getAdapterPosition()).getEmail());
        holder.getPersonAddress().setText(myList.get(holder.getAdapterPosition()).getAddress());

        String imageAddress = "dinesh.jpg";
        try {
            InputStream stream = mContext.getAssets().open(imageAddress);
            Drawable drawable = Drawable.createFromStream(stream, null);
            holder.getPersonImage().setImageDrawable(drawable);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        holder.getMaterialButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(mContext,"Position: "+position,Toast.LENGTH_SHORT).show();
                ViewCompat.setTransitionName(holder.getPersonImage(), "myTransition" + holder.getAdapterPosition());

                Intent intent = new Intent(mContext, DataView.class);
                intent.putExtra("data", myList.get(holder.getAdapterPosition()));
                intent.putExtra("isAdmin", isAdmin);
                intent.putExtra("transitionName", "myTransition" + holder.getAdapterPosition());

                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) mContext,
                        holder.getPersonImage(),
                        Objects.requireNonNull(ViewCompat.getTransitionName(holder.getPersonImage()))
                );

                ((Activity) mContext).startActivityForResult(intent,1, compat.toBundle());
            }
        });

    }


    @Override
    public int getItemCount() {
        return myList.size();
    }
}
