package com.example.rashil.glamstr;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rashil.glamstr.databinding.FragmentLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {
    FragmentLoginBinding fragmentLoginBinding;

    onLoginFormActivityListner loginFormActivityListner;
    public interface onLoginFormActivityListner{
        public void performRegister();
        public void performLogin(String name);
    }



    public Login() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentLoginBinding= FragmentLoginBinding.inflate(inflater,container,false);



        fragmentLoginBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "CLicked", Toast.LENGTH_SHORT).show();
                performLogin();
            }
        });
        fragmentLoginBinding.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginFormActivityListner.performRegister();

            }
        });


        return  fragmentLoginBinding.getRoot();
    }

    private void performLogin() {
        String username = fragmentLoginBinding.username.getText().toString();
        String password = fragmentLoginBinding.password.getText().toString();
        Call<User> call = MainActivity.apiInterface.performLogin(username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok")){
                    MainActivity.prefConfig.writeLoginStatus(true);
                    loginFormActivityListner.performLogin(response.body().getName());

                }
                else if (response.body().getResponse().equals("failed"))
                {
                    MainActivity.prefConfig.displayToast("Login Failed.. Please try again");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
//        UserName.setText("");
//        UserPassword.setText("");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFormActivityListner = (onLoginFormActivityListner) activity;
    }
}
