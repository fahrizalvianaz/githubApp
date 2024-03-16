package com.example.myapplication.ui;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import com.example.myapplication.adapter.SectionPagerAdapter;
//import com.example.myapplication.data.local.entity.FavoriteEntity;
import com.example.myapplication.data.local.entity.FavoriteEntity;
import com.example.myapplication.databinding.ActivityDetailBinding;
import com.example.myapplication.factory.DetailViewModelFactory;
import com.example.myapplication.viewmodel.DetailViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private boolean isFavorite = false;

    public static final String KEY_USER = "user";
    public static final String KEY_USERNAME = "username";
    @StringRes
    private static final int[] TAB_TITLES = new int[]{
            R.string.tab_text_1,
            R.string.tab_text_2
    };
    public static String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FavoriteEntity favorite = new FavoriteEntity();
        DetailViewModel detailViewModel = new ViewModelProvider(this, new DetailViewModelFactory(username, getApplication())).get(DetailViewModel.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            username = getIntent().getStringExtra("username");
            detailViewModel.findUserDetails(username);
        } else {
            username = getIntent().getStringExtra("username");
            detailViewModel.findUserDetails(username);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        detailViewModel.avatarUrl.observe(this, avatar -> {
            Glide.with(this)
                    .load(avatar)
                    .into(binding.ivDetail);
            favorite.setAvatarUrl(avatar);
        });

        detailViewModel.username.observe(this, login -> {
            binding.tvUsernameDetail.setText(login);
            favorite.setUsername(login);
        });

        detailViewModel.name.observe(this, name -> binding.tvNamaDetail.setText(name));

        detailViewModel.followers.observe(this, followers -> binding.followers.setText(String.valueOf(followers)));

        detailViewModel.following.observe(this, following -> binding.following.setText(String.valueOf(following)));

        detailViewModel.isLoading.observe(this, this::showLoading);

        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(this);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionPagerAdapter);

        TabLayout tabs = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabs, viewPager, (tab, position) -> tab.setText(getResources().getString(TAB_TITLES[position]))).attach();

        detailViewModel.getFavoriteByUsername(username)
                .observe(this, listFav -> {
                    isFavorite = !listFav.isEmpty();
                    binding.detailFabFavorite.setImageDrawable(
                            listFav.isEmpty() ?
                                    ContextCompat.getDrawable(binding.detailFabFavorite.getContext(), R.drawable.ic_favorite_border) :
                                    ContextCompat.getDrawable(binding.detailFabFavorite.getContext(), R.drawable.ic_favorite)
                    );
                    binding.detailFabFavorite.setImageTintList(ColorStateList.valueOf(Color.rgb(247, 106, 123)));
                });

        binding.detailFabFavorite.setOnClickListener(view -> {
            if (isFavorite) {
                detailViewModel.delete(favorite);
                Toast.makeText(DetailActivity.this, favorite.getUsername() + " berhasil dihapus dari Favorite ", Toast.LENGTH_LONG).show();
            } else {
                detailViewModel.insert(favorite);
                Toast.makeText(DetailActivity.this, favorite.getUsername() + " berhasil ditambahkan ke Favorite", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showLoading(boolean isLoading) {
        binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }
}
