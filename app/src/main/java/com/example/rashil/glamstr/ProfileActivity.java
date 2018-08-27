package com.example.rashil.glamstr;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.example.rashil.glamstr.databinding.ActivityProfileBinding;
import com.example.rashil.glamstr.databinding.NestedScrollViewContentBinding;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding ProfileBinding;
    NestedScrollViewContentBinding nestedScrollViewContentBinding;
    ExpandableRelativeLayout expandableLayout1;
    ProfileAdapter mAdapter;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.profile2,R.drawable.profile3,R.drawable.profile4, R.drawable.profile5};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    private List<Profile> profileList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ProfileBinding= DataBindingUtil.setContentView(this,R.layout.activity_profile);
        ProfileBinding.profileactivityCollapsingToolbar.setTitle("Naveena Jain");
        ProfileBinding.profileactivityCollapsingToolbar.setContentScrimColor(Color.BLACK);
        ProfileBinding.profileactivityCollapsingToolbar.setExpandedTitleColor(Color.WHITE);
        ProfileBinding.profileactivityCollapsingToolbar.setCollapsedTitleGravity(0);
        ProfileBinding.profileactivityCollapsingToolbar.setExpandedTitleMarginStart(0);


        mAdapter = new ProfileAdapter(profileList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        ProfileBinding.recyclerView1.setLayoutManager(mLayoutManager);
        ProfileBinding.recyclerView1.setItemAnimator(new DefaultItemAnimator());
        ProfileBinding.recyclerView1.setAdapter(mAdapter);
        prepareProfileData();
        init();
    }

    private void prepareProfileData() {
        Profile profile = new Profile("Name", "Eligibility", "Gender", "City", "Education", "Skin", "Eyes", "Martial", "Languages", "Training", "Passport", "CHW", "Biography", "Height", "Age");
        profileList.add(profile);
    }

    public void expandableTextView1(View view) {
//        expandableLayout1 = findViewById(R.id.expandableLayout1);
        ProfileBinding.expandableLayout1.toggle();
    }
    private void init() {
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImagePagerAdapter(ProfileActivity.this,ImagesArray));

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
}