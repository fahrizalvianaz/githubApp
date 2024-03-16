package com.example.myapplication.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemUserBinding
import com.example.myapplication.data.remote.response.ItemsItem
import com.example.myapplication.ui.DetailActivity


class UserAdapter(private val listUser : List<ItemsItem>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(var binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.username.text = listUser[position].login
        val avatarURL = listUser[position].avatarUrl
        Glide.with(holder.itemView.context)
            .load(avatarURL)
            .into(holder.binding.imageView)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("username", listUser[position].login)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = listUser.size
}