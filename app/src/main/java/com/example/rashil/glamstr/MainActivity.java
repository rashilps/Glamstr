package com.example.rashil.glamstr;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.rashil.glamstr.databinding.ActivityMainBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding MainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        MainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(i);

//                checkLogin();
            }
        });
        MainBinding.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);

            }
        });
    }

    public void checkLogin() {

        final String email = MainBinding.username.getText().toString();
        if (!isValidEmail(email)) {
            //Set error message for email field
         MainBinding.username.setError("Invalid Email");
        }

        final String pass = MainBinding.password.getText().toString();
        if (!isValidPassword(pass)) {
            //Set error message for password field
            MainBinding.password.setError("Password cannot be empty");
        }

        if (isValidEmail(email) && isValidPassword(pass)) {
            // Validation Completed
            Intent i = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(i);
        }

    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 4) {
            return true;
        }
        return false;
    }
}

