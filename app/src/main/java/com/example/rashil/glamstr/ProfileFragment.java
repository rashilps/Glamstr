package com.example.rashil.glamstr;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.rashil.glamstr.databinding.ActivityProfileBinding;
import com.example.rashil.glamstr.databinding.NestedScrollViewContentBinding;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ProfileFragment extends Fragment {
    ActivityProfileBinding ProfileBinding;
    ExpandableRelativeLayout expandableLayout1;
    ProfileAdapter mAdapter;
    private ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.profile2,R.drawable.profile3,R.drawable.profile4, R.drawable.profile5};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    private List<Profile> profileList = new ArrayList<>();

    OnLogoutListener logoutListener;

    public interface OnLogoutListener{
        public void logoutPerformed();
    }

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ProfileBinding= ActivityProfileBinding.inflate(inflater,container,false);
        mPager=ProfileBinding.pager;
        ProfileBinding.profileactivityCollapsingToolbar.setTitle("Welcome "+ MainActivity.prefConfig.readName());
        ProfileBinding.profileactivityCollapsingToolbar.setContentScrimColor(Color.BLACK);
        ProfileBinding.profileactivityCollapsingToolbar.setExpandedTitleColor(Color.WHITE);
        ProfileBinding.profileactivityCollapsingToolbar.setCollapsedTitleGravity(0);
        ProfileBinding.profileactivityCollapsingToolbar.setExpandedTitleMarginStart(0);


        mAdapter = new ProfileAdapter(profileList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        ProfileBinding.recyclerView1.setLayoutManager(mLayoutManager);
        ProfileBinding.recyclerView1.setItemAnimator(new DefaultItemAnimator());
        ProfileBinding.recyclerView1.setAdapter(mAdapter);
        prepareProfileData();

        init();


        return ProfileBinding.getRoot();
    }


    private void prepareProfileData() {
        Profile profile = new Profile("Name", "Eligibility", "Gender", "City", "Education", "Skin", "Eyes", "Martial", "Languages", "Training", "Passport", "CHW", "Biography", "Height", "Age");
        profileList.add(profile);
    }

    private void init() {
        ImagesArray.addAll(Arrays.asList(IMAGES));

        mPager.setAdapter(new SlidingImagePagerAdapter(getActivity(),ImagesArray));

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
//        Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                h andler.post(Update);
//            }
//        }, 3000, 3000);

}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ProfileBinding.expandable1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileBinding.expandableLayout1.toggle();
            }
        });
        ProfileBinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 logoutListener.logoutPerformed();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        logoutListener = (OnLogoutListener) activity;

    }
}