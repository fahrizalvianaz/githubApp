package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.data.remote.response.ItemsItem;
import com.example.myapplication.databinding.ItemUserBinding;
import com.example.myapplication.ui.DetailActivity;
//import com.example.myapplication.ui.DetailActivity;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private final List<ItemsItem> listUser;

    public UserAdapter(List<ItemsItem> listUser) {
        this.listUser = listUser;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemUserBinding binding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemsItem user = listUser.get(position);

        holder.binding.username.setText(user.getLogin());
        String avatarURL = user.getAvatarUrl();
        Context context = holder.itemView.getContext();
        Glide.with(context)
            .load(avatarURL)
            .into(holder.binding.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("username", user.getLogin());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemUserBinding binding;

        public ViewHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
