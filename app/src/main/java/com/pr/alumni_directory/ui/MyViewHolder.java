package com.pr.alumni_directory.ui;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.pr.alumni_directory.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView getPersonName() {
        return personName;
    }

    public TextView getPersonRole() {
        return personRole;
    }

    public TextView getPersonJob() {
        return personJob;
    }

    public TextView getPersonPhone() {
        return personPhone;
    }

    public TextView getPersonEmail() {
        return personEmail;
    }

    public TextView getPersonAddress() {
        return personAddress;
    }

    private TextView personName;
    private TextView personRole;
    private TextView personJob;
    private TextView personPhone;
    private TextView personEmail;
    private TextView personAddress;

    public ShapeableImageView getPersonImage() {
        return personImage;
    }

    private ShapeableImageView personImage;

    public MaterialButton getMaterialButton() {
        return materialButton;
    }

    private MaterialButton materialButton;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        personName = itemView.findViewById(R.id.av_personName);
        personRole = itemView.findViewById(R.id.av_personRole);
        personJob = itemView.findViewById(R.id.av_personJob);
        personPhone = itemView.findViewById(R.id.av_personPhone);
        personEmail = itemView.findViewById(R.id.personEmail);
        personAddress = itemView.findViewById(R.id.personLocation);
        personImage = itemView.findViewById(R.id.av_personImage);
        materialButton=itemView.findViewById(R.id.av_knowMore);
    }
}
