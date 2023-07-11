package com.adrian.talentclassgithubapi.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.talentclassgithubapi.R
import com.adrian.talentclassgithubapi.adapter.RecyclerviewOfNameAdapter
import com.adrian.talentclassgithubapi.api.ApiConfig
import com.adrian.talentclassgithubapi.api.FollowersGitHubModel
import com.adrian.talentclassgithubapi.api.TalentHubGitHubUserApiService
import com.adrian.talentclassgithubapi.api.UserGitHubModel
import com.adrian.talentclassgithubapi.data.UserDetail
import com.adrian.talentclassgithubapi.databinding.FragmentFollowersBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)

        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}