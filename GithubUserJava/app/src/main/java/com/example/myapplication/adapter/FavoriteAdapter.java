package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.data.local.entity.FavoriteEntity;
import com.example.myapplication.databinding.ItemUserBinding;
import com.example.myapplication.ui.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private final ArrayList<FavoriteEntity> userFavorite = new ArrayList<>();
    private OnItemClickCallback onItemClickCallback;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemUserBinding binding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(userFavorite.get(position));
        holder.itemView.setOnClickListener(view -> onItemClickCallback.onItemClicked(userFavorite.get(position)));
    }

    @Override
    public int getItemCount() {
        return userFavorite.size();
    }

    public void setListFavorite(List<FavoriteEntity> items) {
        userFavorite.clear();
        userFavorite.addAll(items);
        notifyDataSetChanged();
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(FavoriteEntity favEntity);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemUserBinding binding;

        public ViewHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(FavoriteEntity favoriteEntity) {
            Context context = itemView.getContext();
            Glide.with(itemView)
                .load(favoriteEntity.getAvatarUrl())
                .into(binding.imageView);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.KEY_USER, favoriteEntity);
                context.startActivity(intent);
            });

            binding.username.setText(favoriteEntity.getUsername());
        }
    }
}
