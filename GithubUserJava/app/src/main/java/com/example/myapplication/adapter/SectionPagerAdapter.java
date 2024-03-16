package com.example.myapplication.adapter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ui.DetailActivity;
import com.example.myapplication.ui.FollowFragment;

public class SectionPagerAdapter extends FragmentStateAdapter {

    private final String username = DetailActivity.username;

    public SectionPagerAdapter(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public Fragment createFragment(int position) {
        FollowFragment fragment = new FollowFragment();
        Bundle args = new Bundle();
        args.putInt(FollowFragment.ARG_POSITION, position + 1);
        args.putString(FollowFragment.ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }
}
