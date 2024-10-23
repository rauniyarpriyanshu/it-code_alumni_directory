package com.pr.alumni_directory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.pr.alumni_directory.R;
import com.pr.alumni_directory.db.LoginInfo;

import org.w3c.dom.Text;

import java.util.Objects;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        TextInputEditText userNameEdit = findViewById(R.id.login_view_username_text);
        TextInputEditText passwordEdit = findViewById(R.id.login_view_password_text);
        MaterialButton loginClick = findViewById(R.id.login_view_loginBtn);
        loginClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.requireNonNull(userNameEdit.getText()).toString().equals("admin")) {
                    if (Objects.requireNonNull(passwordEdit.getText()).toString().equals("password")) {
                        LoginInfo info = new LoginInfo(userNameEdit.getText().toString(), passwordEdit.getText().toString());
                        info.setAdmin(true);
                        Intent intent = new Intent(Login.this, Home.class);

                        intent.putExtra("user_d", info);
                        Login.this.startActivity(intent);
                        Login.this.finish();

                    } else {
                        Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                } else if (userNameEdit.getText().toString().equals("rauniyarp")) {
                    if (Objects.requireNonNull(passwordEdit.getText()).toString().equals("passkey1")) {
                        LoginInfo info = new LoginInfo(userNameEdit.getText().toString(), passwordEdit.getText().toString());
                        info.setAdmin(false);
                        Intent intent = new Intent(Login.this, Home.class);

                        intent.putExtra("user_d", info);
                        Login.this.startActivity(intent);
                        Login.this.finish();

                    } else {
                        Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Wrong Login Details!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}