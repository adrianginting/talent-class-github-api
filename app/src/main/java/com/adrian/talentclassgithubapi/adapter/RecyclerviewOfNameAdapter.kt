package com.adrian.talentclassgithubapi.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.adrian.talentclassgithubapi.ProfileDetailActivity
import com.adrian.talentclassgithubapi.api.UserGitHubModel
import com.adrian.talentclassgithubapi.databinding.ItemRowProfileBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


class RecyclerviewOfNameAdapter : RecyclerView.Adapter<RecyclerviewOfNameAdapter.RecyclerviewOfNameViewHolder>() {

    private var listOfName = ArrayList<UserGitHubModel>()

    fun addedListOfUsers(list : List<UserGitHubModel>) {
        this.listOfName.clear()
        this.listOfName.addAll(list)
        notifyDataSetChanged()
    }

    inner class RecyclerviewOfNameViewHolder(private val binding : ItemRowProfileBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(position : Int) {
            val itemNow = listOfName[position]
            binding.tvItemName.text = itemNow.userName

            Glide
                .with(itemView.context)
                .load(itemNow.avatarURL)
                .transform(CenterInside(), RoundedCorners(12))
                .into(binding.imgItemPhoto)

            binding.cardView.setOnClickListener {
                Toast.makeText(itemView.context, "Membuka "+itemNow.userName, Toast.LENGTH_SHORT).show()
                val intent = Intent(binding.cardView.context, ProfileDetailActivity::class.java)
                intent.putExtra("name", itemNow.userName)
                binding.cardView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerviewOfNameViewHolder {
        return RecyclerviewOfNameViewHolder(
            ItemRowProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listOfName.size
    }

    override fun onBindViewHolder(holder: RecyclerviewOfNameViewHolder, position: Int) {
        holder.bind(position)
    }

}