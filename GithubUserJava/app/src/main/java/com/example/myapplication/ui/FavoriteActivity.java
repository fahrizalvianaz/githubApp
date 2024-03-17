package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.adapter.FavoriteAdapter;
import com.example.myapplication.databinding.ActivityFavoriteBinding;
import com.example.myapplication.factory.FavoriteViewModelFactory;
import com.example.myapplication.viewmodel.FavoriteViewModel;

public class FavoriteActivity extends AppCompatActivity {

    private ActivityFavoriteBinding binding;
    private FavoriteViewModel favoriteViewModel;
    private final FavoriteAdapter adapter = new FavoriteAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        favoriteViewModel = obtainViewModel(this);
        setUpList();
        setUserFavorite();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private FavoriteViewModel obtainViewModel(AppCompatActivity activity) {
        FavoriteViewModelFactory factory = FavoriteViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(FavoriteViewModel.class);
    }

    private void setUpList() {
        binding.rvFavorite.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        binding.rvFavorite.addItemDecoration(itemDecoration);
        binding.rvFavorite.setAdapter(adapter);

        adapter.setOnItemClickCallback(favEntity -> {
            Intent intent = new Intent(FavoriteActivity.this, DetailActivity.class);
            intent.putExtra(DetailActivity.KEY_USERNAME, favEntity.getUsername());
            startActivity(intent);
        });
    }

    private void setUserFavorite() {
        favoriteViewModel = obtainViewModel(this);
        favoriteViewModel.getAllFavorites().observe(this, favList -> {
            if (favList != null) {
                adapter.setListFavorite(favList);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
