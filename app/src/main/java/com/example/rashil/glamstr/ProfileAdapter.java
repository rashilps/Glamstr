package com.example.rashil.glamstr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rashil on 16/7/18.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder> {

    private List<Profile> profileList;
    private Context mctx;

    ProfileAdapter(List<Profile> profileList, Context mctx) {
        this.profileList = profileList;
        this.mctx = mctx;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(mctx)
                .inflate(R.layout.list_item, null, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Profile profile = profileList.get(position);
        holder.name.setText(profile.getName());
        holder.eligibility.setText(profile.getEligibility());
        holder.age.setText(String.valueOf(profile.getAge()));
        holder.gender.setText(profile.getGender());
        holder.city.setText(profile.getCity());
        holder.height.setText(String.valueOf(profile.getHeight()));
        holder.education.setText(profile.getEducation());
        holder.skin.setText(profile.getSkin());
        holder.eyes.setText(profile.getEyes());
        holder.martial.setText(profile.getMartial());
        holder.languages.setText(profile.getLanguages());
        holder.training.setText(profile.getTraining());
        holder.passport.setText(profile.getPassport());
        holder.cwh.setText(profile.getCwh());
        holder.biography.setText(profile.getBiography());
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, height, eligibility, gender, city, education, skin, eyes, martial, languages, training, passport, cwh, biography, age;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);

            eligibility = view.findViewById(R.id.eligibility);
            age = view.findViewById(R.id.age);
            gender = view.findViewById(R.id.gender);
            city = view.findViewById(R.id.city);
            height = view.findViewById(R.id.height);
            education = view.findViewById(R.id.education);
            skin = view.findViewById(R.id.skin);
            eyes = view.findViewById(R.id.eyes);
            martial = view.findViewById(R.id.martial);
            languages = view.findViewById(R.id.languages);
            training = view.findViewById(R.id.training);
            passport = view.findViewById(R.id.passport);
            cwh = view.findViewById(R.id.cwh);
            biography = view.findViewById(R.id.biography);


        }
    }
}
