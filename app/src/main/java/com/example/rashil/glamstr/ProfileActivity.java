package com.example.rashil.glamstr;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.example.rashil.glamstr.databinding.ActivityProfileBinding;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding ProfileBinding;
    ExpandableRelativeLayout expandableLayout1;
    ProfileAdapter mAdapter;

    private List<Profile> profileList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

/// /
//        CollapsingToolbarLayout collapsingToolbarLayout =
//                findViewById(R.id.profileactivity_collapsing_toolbar);

        ProfileBinding.profileactivityCollapsingToolbar.setTitle("Naveena Jain");
        ProfileBinding.profileactivityCollapsingToolbar.setContentScrimColor(Color.BLACK);
        ProfileBinding.profileactivityCollapsingToolbar.setExpandedTitleColor(Color.WHITE);
        ProfileBinding.profileactivityCollapsingToolbar.setCollapsedTitleGravity(0);
        ProfileBinding.profileactivityCollapsingToolbar.setExpandedTitleMarginStart(0);


//        recyclerView = findViewById(R.id.recyclerView1);
        mAdapter = new ProfileAdapter(profileList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        ProfileBinding.recyclerView1.setLayoutManager(mLayoutManager);
        ProfileBinding.recyclerView1.setItemAnimator(new DefaultItemAnimator());
        ProfileBinding.recyclerView1.setAdapter(mAdapter);
        prepareProfileData();
    }

    private void prepareProfileData() {
        Profile profile = new Profile("Name", "Eligibility", "Gender", "City", "Education", "Skin", "Eyes", "Martial", "Languages", "Training", "Passport", "CHW", "Biography", "Height", "Age");
        profileList.add(profile);
    }

    public void expandableTextView1(View view) {
//        expandableLayout1 = findViewById(R.id.expandableLayout1);
        ProfileBinding.expandableLayout1.toggle();
    }
}