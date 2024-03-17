package com.example.myapplication.ui;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.myapplication.R;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.data.local.datastore.SettingPreferences;
import com.example.myapplication.data.remote.response.ItemsItem;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.factory.MainViewModelFactory;
import com.example.myapplication.factory.SettingViewModelFactory;
import com.example.myapplication.viewmodel.MainViewModel;
import com.example.myapplication.viewmodel.SettingViewModel;


import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener {

    private ActivityMainBinding binding;
    private SettingViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvItem.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        binding.rvItem.addItemDecoration(itemDecoration);

        MainViewModel mainViewModel = new ViewModelProvider(this, new MainViewModelFactory()).get(MainViewModel.class);

        mainViewModel.user.observe(this, this::setUserData);
        mainViewModel.isLoading.observe(this, this::showLoading);
        SettingPreferences preferences = new SettingPreferences(this);
        viewModel = new ViewModelProvider(this, new SettingViewModelFactory(preferences)).get(SettingViewModel.class);
        if (viewModel.isDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        getMenuInflater().inflate(R.menu.option_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        MenuItem favoriteView = menu.findItem(R.id.favorite);
        MenuItem settingView = menu.findItem(R.id.setting);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mainViewModel.findUser(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        favoriteView.setOnMenuItemClickListener(this);
        settingView.setOnMenuItemClickListener(this);

        return true;
    }

    private void setUserData(List<ItemsItem> dataUser) {
        List<ItemsItem> getUserData = dataUser.stream()
                .map(item -> new ItemsItem(item.getLogin(), item.getAvatarUrl()))
                .collect(Collectors.toList());

        UserAdapter adapter = new UserAdapter(getUserData);
        binding.rvItem.setAdapter(adapter);
    }

    private void showLoading(boolean isLoading) {
        binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite:
//                Toast.makeText(this, "Favorite", Toast.LENGTH_SHORT).show();
                Intent favoriteIntent = new Intent(this, FavoriteActivity.class);
                startActivity(favoriteIntent);
                return true;
            case R.id.setting:
//                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                Intent settingIntent = new Intent(this, SettingActivity.class);
                startActivity(settingIntent);
                return true;
            default:
                return false;
        }
    }
}
