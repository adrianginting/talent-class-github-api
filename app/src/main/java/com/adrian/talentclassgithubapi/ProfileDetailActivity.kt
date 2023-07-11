package com.adrian.talentclassgithubapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.adrian.talentclassgithubapi.adapter.SectionsPagerAdapter
import com.adrian.talentclassgithubapi.api.ApiConfig
import com.adrian.talentclassgithubapi.data.UserDetail
import com.adrian.talentclassgithubapi.databinding.ActivityProfileDetailBinding
import com.adrian.talentclassgithubapi.fragment.FollowersFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileDetailBinding

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.follwer,
            R.string.follwing
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.extras
        val username = intent?.getString("name")

        getData(username!!)
        sectionsPager()

    }

    private fun getData(username: String){
        val apiService = ApiConfig.getApiService()
        val call = apiService.getUserDetail(username)
        call.enqueue(object : Callback<UserDetail> {
            override fun onResponse(
                call: Call<UserDetail>,
                response: Response<UserDetail>
            ) {
                if (response.isSuccessful) {
                    val userDetail = response.body()
                    if (userDetail != null) {
                        Glide.with(this@ProfileDetailActivity)
                            .load(userDetail.avatarUrl)
                            .transform(CenterInside(), RoundedCorners(24))
                            .into(binding.avatars)
                        binding.fullName.text = userDetail.name
                        binding.username.text = userDetail.login
                        binding.company.text = userDetail.company
                        binding.location.text = userDetail.location
                        binding.repositories.text = userDetail.publicRepos.toString()
                        binding.followers.text = userDetail.followers.toString()
                        binding.following.text = userDetail.following.toString()

                    }
                } else {
                    // Tangani kesalahan jika ada
                }
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                // Tangani kesalahan jaringan atau koneksi jika ada
            }
        })
    }

    private fun sectionsPager(){
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }
}