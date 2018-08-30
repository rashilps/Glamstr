package com.example.rashil.glamstr;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rashil.glamstr.databinding.FragmentLoginBinding;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLoginBinding= DataBindingUtil.setContentView(getActivity(),R.layout.fragment_login);
        View view = inflater.inflate(R.layout.fragment_login, container, false);



        fragmentLoginBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileFragment profileFragment = new ProfileFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, profileFragment);
                ft.commit();

//                checkLogin();
            }
        });
        fragmentLoginBinding.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginFormActivityListner.performRegister();

            }
        });


        return  view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFormActivityListner = (onLoginFormActivityListner) activity;
    }
}
