package com.pr.alumni_directory.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.pr.alumni_directory.R;
import com.pr.alumni_directory.data.AlumniData;
import com.pr.alumni_directory.fragment.ActivityInterface;

import java.io.IOException;
import java.io.InputStream;

public class DataView extends AppCompatActivity implements ActivityInterface {

    private TextView name;
    private TextView email;
    private TextView job;
    private TextView address;
    private TextView businessName;
    private TextView businessEmail;
    private TextView businessPhone;
    private TextView businessAddress;
    private TextView businessWeb;
    private TextView businessCategory;
    private TextView businessDescription;
    AlumniData data;
    ImageView dataImage;
    ImageView back;
    ImageView edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);

        screenInitialize();
        appComponentLoad();
    }

    @Override
    public void screenInitialize() {

        name = findViewById(R.id.dataview_name);
        email = findViewById(R.id.dataview_email);
        address = findViewById(R.id.dataview_location);
        job = findViewById(R.id.dataView_jobTitle);

        businessName = findViewById(R.id.dataview_businessName);
        businessEmail = findViewById(R.id.dataview_businessEmail);
        businessPhone = findViewById(R.id.dataview_businessPhone);

        businessAddress = findViewById(R.id.dataview_businessLocation);
        businessWeb = findViewById(R.id.dataview_businessWebsiteUrl);
        businessCategory = findViewById(R.id.dataview_businessCategory);
        businessDescription = findViewById(R.id.dataview_businessDescription);

        dataImage = findViewById(R.id.data_view_image);
        back = findViewById(R.id.dataView_back);
        edit = findViewById(R.id.dataView_edit);
    }

    @Override
    public void appComponentLoad() {


        data = (AlumniData) getIntent().getSerializableExtra("data");

        String transitionName = getIntent().getStringExtra("myTransition");

        ViewCompat.setTransitionName(dataImage, transitionName);
        if (data != null) {
            try {
                InputStream stream = DataView.this.getAssets().open("dinesh.jpg");
                Drawable drawable = Drawable.createFromStream(stream, null);
                dataImage.setImageDrawable(drawable);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        postponeEnterTransition();

        dataImage.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                startPostponedEnterTransition();
                return true;
            }
        });

        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                supportFinishAfterTransition();
                DataView.this.finish();
            }
        });


        boolean isAdmin = getIntent().getBooleanExtra("isAdmin", false);

        if (!isAdmin) {
            edit.setVisibility(View.GONE);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataView.this.finish();
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent($activity(), EditDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("isAdmin", true);
                if (data != null) {
                    intent.putExtra("data", data);
                }

                $activity().startActivity(intent);
                $activity().finish();

            }
        });

        if (data != null) {
            name.setText(data.getName());
            email.setText(data.getEmail());
            address.setText(data.getAddress());
            job.setText(data.getJobTitle());
            businessName.setText(data.getSubData().getNameOfBusiness());
            businessEmail.setText(data.getSubData().getBusinessEmail());
            businessPhone.setText(data.getSubData().getBusinessPhone());
            String address = data.getSubData().getBusinessAddress() + ", " + data.getSubData().getBusinessCity() + ", " + data.getSubData().getBusinessState() + " " + data.getSubData().getBusinessZip();
            businessAddress.setText(address);
            businessWeb.setText(data.getSubData().getBusinessWebsite());
            businessCategory.setText(data.getSubData().getBusinessCategory());
            businessDescription.setText(data.getSubData().getBusinessDescription());
        }


    }

    @Override
    public Activity $activity() {
        return this;
    }

    @Override
    public Context $context() {
        return this;
    }
}