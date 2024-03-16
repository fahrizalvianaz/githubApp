package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.local.entity.FavoriteEntity
import com.example.myapplication.databinding.ItemUserBinding
import com.example.myapplication.ui.DetailActivity

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private val userFavorite = ArrayList<FavoriteEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = userFavorite.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userFavorite[position])
        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(userFavorite[position])}
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListFavorite(items: List<FavoriteEntity>) {
        userFavorite.clear()
        userFavorite.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteEntity: FavoriteEntity) {
            with(binding) {
                Glide.with(root)
                    .load(favoriteEntity.avatarUrl)
                    .into(binding.imageView)
                root.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.KEY_USER, favoriteEntity)
                    itemView.context.startActivity(intent)
                }
                binding.username.text = favoriteEntity.username
            }
        }

    }
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(favEntity: FavoriteEntity)
    }

}