package com.example.rashil.glamstr;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.rashil.glamstr.databinding.ActivityRegisterBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class RegisterFragment extends Fragment {
    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;
    ActivityRegisterBinding activityRegisterBinding;
    public RegisterFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        activityRegisterBinding = DataBindingUtil.setContentView(getActivity(), R.layout.activity_register);
        activityRegisterBinding= DataBindingUtil.setContentView(getActivity(),R.layout.activity_register);



        View view =  inflater.inflate(R.layout.activity_register, container, false);

        activityRegisterBinding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


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

        String Image = imageToString();
        ApiInterface apiInterface = ApiClient.getApiCLient().create(ApiInterface.class);
        Call<User> call1 = apiInterface.uploadImage(Image);
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call1, Response<User> response) {
                User imageClass = response.body();
                Toast.makeText(getActivity(), "Server Response: "+imageClass.getResponse(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<User> call1, Throwable t) {

            }
        });


//        activityRegisterBinding.inputStudentName.setText("");
//        activityRegisterBinding.inputStudentUserName.setText("");
//        activityRegisterBinding.inputStudentPassword.setText("");
    }
    public void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMG_REQUEST && resultCode == RESULT_OK && data!=null){
            Uri path = data.getData();

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(),path);
                    activityRegisterBinding.imageView.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }
}
