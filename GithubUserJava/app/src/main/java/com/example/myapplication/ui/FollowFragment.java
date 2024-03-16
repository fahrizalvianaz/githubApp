package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.databinding.FragmentFollowBinding;
import com.example.myapplication.data.remote.response.ItemsItem;
import com.example.myapplication.factory.FollowViewModelFactory;
import com.example.myapplication.viewmodel.FollowViewModel;

import java.util.List;
import java.util.stream.Collectors;

public class FollowFragment extends Fragment {

    private FragmentFollowBinding binding;

    public static final String ARG_POSITION = "position";
    public static final String ARG_USERNAME = "username";

    private int position = 0;
    private String username = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFollowBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FollowViewModel mainViewModel = new ViewModelProvider(this, new FollowViewModelFactory(username)).get(FollowViewModel.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity());
        binding.rvFollow.setLayoutManager(layoutManager);

        mainViewModel.follow.observe(getViewLifecycleOwner(), this::setDataFollow);
        mainViewModel.isLoading.observe(requireActivity(), this::showLoading);

        Bundle arguments = getArguments();
        if (arguments != null) {
            position = arguments.getInt(ARG_POSITION);
            username = arguments.getString(ARG_USERNAME, "");
        }

        if (position == 1) {
            mainViewModel.findUserFollowers(username);
        } else {
            mainViewModel.findUserFollowing(username);
        }
    }

    private void setDataFollow(List<ItemsItem> dataUserFollow) {
        List<ItemsItem> getUserData = dataUserFollow.stream()
                .map(item -> new ItemsItem(item.getLogin(), item.getAvatarUrl()))
                .collect(Collectors.toList());

        UserAdapter adapter = new UserAdapter(getUserData);
        binding.rvFollow.setAdapter(adapter);
    }

    private void showLoading(boolean isLoading) {
        binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }
}
