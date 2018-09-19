package com.example.rashil.glamstr;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.rashil.glamstr.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements Login.onLoginFormActivityListner,ProfileFragment.OnLogoutListener {
    ActivityMainBinding activityMainBinding;
    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main);


        prefConfig = new PrefConfig(this);
        apiInterface = ApiClient.getApiCLient().create(ApiInterface.class);


        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState!=null){
                return;
            }
            if (prefConfig.readLoginStatus()){
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new ProfileFragment()).commit();
            }
            else
            {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new Login()).addToBackStack(null).commit();
            }
        }



    }

    @Override
    public void performRegister() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,new RegisterFragment())
                .commit();
    }

    @Override
    public void performLogin(String name) {
        prefConfig.writeName(name);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
    }


    @Override
    public void logoutPerformed() {
        prefConfig.writeLoginStatus(false);
        prefConfig.writeName("User");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Login()).commit();
    }
//    public void checkLogin() {
//
//        final String email = MainBinding.username.getText().toString();
//        if (!isValidEmail(email)) {
//            //Set error message for email field
//         MainBinding.username.setError("Invalid Email");
//        }
//
//        final String pass = MainBinding.password.getText().toString();
//        if (!isValidPassword(pass)) {
//            //Set error message for password field
//            MainBinding.password.setError("Password cannot be empty");
//        }
//
//        if (isValidEmail(email) && isValidPassword(pass)) {
//            // Validation Completed
//            Intent i = new Intent(MainActivity.this, ProfileActivity.class);
//            startActivity(i);
//        }
//
//    }
//
//    // validating email id
//    private boolean isValidEmail(String email) {
//        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//
//        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }
//
//    // validating password
//    private boolean isValidPassword(String pass) {
//        if (pass != null && pass.length() >= 4) {
//            return true;
//        }
//        return false;
//    }
}

