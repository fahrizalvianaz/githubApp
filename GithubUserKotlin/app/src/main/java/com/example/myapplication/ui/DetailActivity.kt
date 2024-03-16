package com.example.myapplication.ui


import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapter.SectionPagerAdapter
import com.example.myapplication.data.local.entity.FavoriteEntity
import com.example.myapplication.databinding.ActivityDetailBinding
import com.example.myapplication.factory.DetailViewModelFactory
import com.example.myapplication.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var isFavorite = false

    companion object {
        const val KEY_USER = "user"
        const val KEY_USERNAME = "username"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
        var username : String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val favorite = FavoriteEntity()
        val detailViewModel : DetailViewModel by viewModels {
            DetailViewModelFactory(username, application)
        }

        if (Build.VERSION.SDK_INT >= 33) {
            username = intent.getStringExtra("username").toString()
            detailViewModel.findUserDetails(username)

        } else {
            @Suppress("DEPRECATION")
            username = intent.getStringExtra("username") as String
            detailViewModel.findUserDetails(username)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailViewModel.avatarUrl.observe(this){ avatar ->
            Glide.with(this)
                .load(avatar)
                .into(binding.ivDetail)
            favorite.avatarUrl = avatar
        }
        detailViewModel.username.observe(this){ login ->
            binding.tvUsernameDetail.text = login
            favorite.username = login!!
        }
        detailViewModel.name.observe(this){ name ->
            binding.tvNamaDetail.text = name
        }
        detailViewModel.followers.observe(this){ followers ->
            binding.followers.text = followers.toString()
        }
        detailViewModel.following.observe(this){ following ->
            binding.following.text = following.toString()
        }

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val sectionPagerAdapter = SectionPagerAdapter(this)
        val viewPager : ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionPagerAdapter
        val tabs : TabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabs,viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        detailViewModel.getFavoriteByUsername(username)
            .observe(this@DetailActivity) { listFav ->
                isFavorite = listFav.isNotEmpty()
                binding.detailFabFavorite.setImageDrawable(
                    if (listFav.isEmpty()) {
                        binding.detailFabFavorite.imageTintList =
                            ColorStateList.valueOf(Color.rgb(247, 106, 123))
                        ContextCompat.getDrawable(
                            binding.detailFabFavorite.context,
                            R.drawable.ic_favorite_border
                        )
                    } else {
                        binding.detailFabFavorite.imageTintList =
                            ColorStateList.valueOf(Color.rgb(247, 106, 123))
                        ContextCompat.getDrawable(
                            binding.detailFabFavorite.context,
                            R.drawable.ic_favorite
                        )
                    }
                )
            }

        binding.detailFabFavorite.apply {
            setOnClickListener {
                if (isFavorite) {
                    detailViewModel.delete(favorite)
                    makeText(
                        this@DetailActivity,
                        "${favorite.username} berhasil dihapus dari Favorite ",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    detailViewModel.insert(favorite)
                    makeText(
                        this@DetailActivity,
                        "${favorite.username} berhasil ditambahkan ke Favorite",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}