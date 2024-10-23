package com.pr.alumni_directory.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.pr.alumni_directory.R;
import com.pr.alumni_directory.data.AlumniData;
import com.pr.alumni_directory.data.AlumniSubData;
import com.pr.alumni_directory.db.CsvLoader;
import com.pr.alumni_directory.db.LoginInfo;
import com.pr.alumni_directory.fragment.ActivityInterface;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.Objects;

public class EditDetails extends AppCompatActivity implements ActivityInterface {
    private TextInputEditText firstName;
    private TextInputEditText lastName;
    private TextInputEditText email;
    private TextInputEditText address;
    private TextInputEditText jobTitle;
    private TextInputEditText businessName;
    private TextInputEditText businessAdresss;
    private TextInputEditText businessCity;
    private TextInputEditText businessState;
    private TextInputEditText businessZip;

    private TextInputEditText businessPhone;
    private TextInputEditText businessEmail;
    private TextInputEditText businessWebsite;
    private TextInputEditText businessDescription;
    private TextInputEditText businessCategory;

    private TextInputEditText alumniDiscount;
    private TextInputEditText alumniDiscountD;

    private TextInputEditText businesslogo;
    private TextInputEditText userId;
    private MaterialButton submitButton;

    private boolean isUpdate = false;
    private ImageView back;
    private final String FILE_PATH = "/databaseFile/database.csv";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);
        screenInitialize();
        checkIfEdit();
        appComponentLoad();
    }

    @Override
    public void screenInitialize() {
        firstName = findViewById(R.id.edit_details_firstName_text);
        lastName = findViewById(R.id.edit_details_lastName_text);
        email = findViewById(R.id.edit_details_email_text);
        address = findViewById(R.id.edit_details_address_text);
        jobTitle = findViewById(R.id.edit_details_jobTitle_text);
        businessName = findViewById(R.id.edit_details_businessName_text);
        businessAdresss = findViewById(R.id.edit_details_businessAddress_text);
        businessCity = findViewById(R.id.edit_details_businessCity_text);
        businessState = findViewById(R.id.edit_details_businessState_text);
        businessZip = findViewById(R.id.edit_details_businessZip_text);
        businessPhone = findViewById(R.id.edit_details_businessPhone_text);
        businessEmail = findViewById(R.id.edit_details_businessEmail_text);
        businessWebsite = findViewById(R.id.edit_details_businessWebsite_text);
        businessDescription = findViewById(R.id.edit_details_businessDescription_text);
        businessCategory = findViewById(R.id.edit_details_businessCategory_text);
        alumniDiscount = findViewById(R.id.edit_details_alumniDiscount_text);
        alumniDiscountD = findViewById(R.id.edit_details_alumniDiscountI_text);
        businesslogo = findViewById(R.id.edit_details_businessLogo_text);
        userId = findViewById(R.id.edit_details_userId_text);
        submitButton = findViewById(R.id.edit_view_submit_btn);
        back = findViewById(R.id.edit_details_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent($context(), Home.class);
                LoginInfo info = new LoginInfo();
                info.setAdmin(true);

                intent.putExtra("user_d", info);
                $activity().startActivity(intent);
                $activity().finish();
            }
        });

        EditDetails.this.getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent($context(), Home.class);
                LoginInfo info = new LoginInfo();
                info.setAdmin(true);

                intent.putExtra("user_d", info);
                $activity().startActivity(intent);
                $activity().finish();
            }
        });


    }

    public void checkIfEdit() {
        boolean isAdmin = $activity().getIntent().getBooleanExtra("isAdmin", false);
        if (isAdmin) {
            AlumniData data = (AlumniData) $activity().getIntent().getSerializableExtra("data");
            if (data != null) {
                firstName.setText(data.getFirstName());
                lastName.setText(data.getLastName());
                email.setText(data.getEmail());
                address.setText(data.getAddress());
                jobTitle.setText(data.getJobTitle());
                AlumniSubData subData = data.getSubData();
                businessName.setText(subData.getNameOfBusiness());
                businessEmail.setText(subData.getBusinessEmail());
                businessAdresss.setText(subData.getBusinessAddress());
                businessState.setText(subData.getBusinessState());
                businessCity.setText(subData.getBusinessCity());
                businessZip.setText(subData.getBusinessZip());
                businessPhone.setText(subData.getBusinessPhone());
                businessWebsite.setText(subData.getBusinessWebsite());
                businessDescription.setText(subData.getBusinessDescription());
                businessCategory.setText(subData.getBusinessCategory());
                businesslogo.setText(subData.getBusinessLogoLink());
                alumniDiscount.setText(subData.getAlumniDiscount());
                alumniDiscountD.setText(subData.getAlumniDiscountInfo());
                userId.setText(subData.getUserId());
                userId.setEnabled(false);
                isUpdate = true;
            }
        }

    }

    @Override
    public void appComponentLoad() {
        CsvLoader loader = new CsvLoader($activity().getExternalCacheDir() + FILE_PATH);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Objects.requireNonNull(firstName.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(lastName.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(email.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(address.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(jobTitle.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(businessName.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(businessAdresss.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(businessCity.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(businessZip.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(businessState.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(businessPhone.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(businessWebsite.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(businessEmail.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(businessDescription.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(businessCategory.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(alumniDiscount.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(businesslogo.getText()).toString().isEmpty()
                        && !Objects.requireNonNull(userId.getText()).toString().isEmpty()) {


                    try {
                        if (alumniDiscount.getText().toString().equalsIgnoreCase("yes")) {
                            if (alumniDiscountD.getText().toString().isEmpty()) {
                                showDialog();
                                return;
                            }
                        } else {
                            alumniDiscountD.setText(" ");
                        }
                        if (isUpdate) {
                            loader.updateCSV(Objects.requireNonNull(userId.getText()).toString(),
                                    firstName.getText().toString()
                                    , Objects.requireNonNull(lastName.getText()).toString(),
                                    Objects.requireNonNull(email.getText()).toString(),
                                    firstName.getText().toString() + " " + lastName.getText().toString(),
                                    Objects.requireNonNull(address.getText()).toString(),
                                    Objects.requireNonNull(jobTitle.getText()).toString(),
                                    Objects.requireNonNull(businessName.getText()).toString(),
                                    Objects.requireNonNull(businessAdresss.getText()).toString(),
                                    Objects.requireNonNull(businessCity.getText()).toString(),
                                    Objects.requireNonNull(businessState.getText()).toString(),
                                    Objects.requireNonNull(businessZip.getText()).toString(),
                                    Objects.requireNonNull(businessPhone.getText()).toString(),
                                    Objects.requireNonNull(businessEmail.getText()).toString(),
                                    Objects.requireNonNull(businessWebsite.getText()).toString(),
                                    Objects.requireNonNull(businessDescription.getText()).toString(),
                                    Objects.requireNonNull(businessCategory.getText()).toString(),
                                    Objects.requireNonNull(alumniDiscount.getText()).toString(),
                                    Objects.requireNonNull(alumniDiscountD.getText()).toString(),
                                    Objects.requireNonNull(businesslogo.getText()).toString()
                            );
                        } else {

                            loader.insertCSV(firstName.getText().toString()
                                    , Objects.requireNonNull(lastName.getText()).toString(),
                                    Objects.requireNonNull(email.getText()).toString(),
                                    firstName.getText().toString() + " " + lastName.getText().toString(),
                                    Objects.requireNonNull(address.getText()).toString(),
                                    Objects.requireNonNull(jobTitle.getText()).toString(),
                                    Objects.requireNonNull(businessName.getText()).toString(),
                                    Objects.requireNonNull(businessAdresss.getText()).toString(),
                                    Objects.requireNonNull(businessCity.getText()).toString(),
                                    Objects.requireNonNull(businessState.getText()).toString(),
                                    Objects.requireNonNull(businessZip.getText()).toString(),
                                    Objects.requireNonNull(businessPhone.getText()).toString(),
                                    Objects.requireNonNull(businessEmail.getText()).toString(),
                                    Objects.requireNonNull(businessWebsite.getText()).toString(),
                                    Objects.requireNonNull(businessDescription.getText()).toString(),
                                    Objects.requireNonNull(businessCategory.getText()).toString(),
                                    Objects.requireNonNull(alumniDiscount.getText()).toString(),
                                    Objects.requireNonNull(alumniDiscountD.getText()).toString(),
                                    Objects.requireNonNull(businesslogo.getText()).toString(),
                                    Objects.requireNonNull(userId.getText()).toString());

                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    showDialog();
                }
            }
        });
    }

    @Override
    public Activity $activity() {
        return this;
    }

    @Override
    public Context $context() {
        return this;
    }

    public void showDialog() {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder($activity())
                .setTitle("Error")
                .setMessage("Please fill all form")
                .setIcon(R.drawable.ic_warning)
                .setCancelable(true)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }
}