package com.adrian.talentclassgithubapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.talentclassgithubapi.adapter.RecyclerviewOfNameAdapter
import com.adrian.talentclassgithubapi.api.ApiConfig
import com.adrian.talentclassgithubapi.api.TalentHubGitHubUserApiService
import com.adrian.talentclassgithubapi.api.UserGitHubModel
import com.adrian.talentclassgithubapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val client = ApiConfig.getApiService()

        settingRVUsers(client)
    }

    private fun settingRVUsers(client: TalentHubGitHubUserApiService) {
        val rvAdapter = RecyclerviewOfNameAdapter()
        val getListUsersFromClient = client.getListUsers()

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = rvAdapter

        showLoading(true)

        getListUsersFromClient.enqueue(object : Callback<List<UserGitHubModel>> {
            override fun onResponse(
                call: Call<List<UserGitHubModel>>,
                response: Response<List<UserGitHubModel>>
            ) {
                showLoading(false)
                if (response.isSuccessful) {

                    val responseBody = response.body()
                    if (!responseBody.isNullOrEmpty()) {
                        rvAdapter.addedListOfUsers(responseBody)
                    }
                } else {
                    Log.e("failedGetListUser", "onFailed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<UserGitHubModel>>, t: Throwable) {
                showLoading(false)
                Log.e("failedGetListUser", "onFailed: ${t.message}")
            }

        })
    }

    private fun showLoading(isShow : Boolean) {
        if (isShow) {
            binding.indeterminateBar.visibility = View.VISIBLE
        } else {
            binding.indeterminateBar.visibility = View.GONE
        }
    }
}