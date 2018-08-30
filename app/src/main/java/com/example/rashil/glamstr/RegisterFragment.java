package com.example.rashil.glamstr;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rashil.glamstr.databinding.ActivityRegisterBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    ActivityRegisterBinding activityRegisterBinding;
    public RegisterFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        activityRegisterBinding = DataBindingUtil.setContentView(getActivity(), R.layout.activity_register);
        activityRegisterBinding= DataBindingUtil.setContentView(getActivity(),R.layout.activity_register);



        View view =  inflater.inflate(R.layout.activity_register, container, false);

        activityRegisterBinding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });
        return view;
    }

    public void performRegistration(){
        String name  = activityRegisterBinding.inputStudentName.getText().toString();
        String username = activityRegisterBinding.inputStudentUserName.getText().toString();
        String password = activityRegisterBinding.inputStudentPassword.getText().toString();
        Call<User> call = MainActivity.apiInterface.performRegistration(name,username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok"))
                {
                    MainActivity.prefConfig.displayToast("Registration Successful");
                }
                else if(response.body().getResponse().equals("exists")){

                    MainActivity.prefConfig.displayToast("User Exists");
                }
                else if(response.body().getResponse().equals("error")){
                    MainActivity.prefConfig.displayToast("Registration Failed");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
//        activityRegisterBinding.inputStudentName.setText("");
//        activityRegisterBinding.inputStudentUserName.setText("");
//        activityRegisterBinding.inputStudentPassword.setText("");
    }
}
