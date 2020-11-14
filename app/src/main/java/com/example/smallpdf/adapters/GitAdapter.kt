package com.example.smallpdf.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smallpdf.R
import com.example.smallpdf.models.UserDetails
import kotlinx.android.synthetic.main.fragment_user_details.view.*

class GitAdapter : RecyclerView.Adapter<GitAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<UserDetails>() {

        override fun areItemsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.repo_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userdetails = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(userdetails.avatarUrl).into(avatarImage)
            name.text = userdetails.name
            company.text = userdetails.company
        }
    }
}