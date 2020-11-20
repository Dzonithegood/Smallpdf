package com.example.smallpdf.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.smallpdf.R
import com.example.smallpdf.models.UserDetails
import com.example.smallpdf.models.UserReposItem
import kotlinx.android.synthetic.main.fragment_commit_details.view.*
import kotlinx.android.synthetic.main.repo_item.view.*

class GitRepoListAdapter : RecyclerView.Adapter<GitRepoListAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    private val list = ArrayList<UserReposItem>()

    fun setList(list: ArrayList<UserReposItem>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

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
//        return differ.currentList.size
        return list.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        val userReposItem = differ.currentList[position]
        val userReposItem = list[position]

        holder.itemView.apply {

//            Toast.makeText(holder.itemView.context, "itemclicked", Toast.LENGTH_SHORT).show()
            textViewRepoName.text = userReposItem.name
            numberOfOpenIssues.text = userReposItem.openIssuesCount.toString()

            setOnClickListener{
                listener?.onItemClicked(userReposItem)
            }
//                onItemClickListener?.let { it(userReposItem) }

        }
    }

//    private var onItemClickListener: ((UserReposItem) -> Unit)? = null

    var listener: OnItemClicked2? = null

//    fun setOnItemClickListener(listener: (UserReposItem) -> Unit) {
//        onItemClickListener = listener
//
//    }

    interface OnItemClicked2 {
        fun onItemClicked(userReposItem: UserReposItem)
    }
}
