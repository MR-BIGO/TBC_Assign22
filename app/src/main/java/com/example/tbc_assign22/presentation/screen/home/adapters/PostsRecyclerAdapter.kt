package com.example.tbc_assign22.presentation.screen.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbc_assign22.R
import com.example.tbc_assign22.databinding.PostItemBinding
import com.example.tbc_assign22.presentation.extension.epochToDate
import com.example.tbc_assign22.presentation.model.PostPresentation

class PostsRecyclerAdapter :
    ListAdapter<PostPresentation, PostsRecyclerAdapter.PostsViewHolder>(DiffCallback()) {

    inner class PostsViewHolder(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            val post = currentList[adapterPosition]
            tvName.text = post.owner.firstName.plus(" ").plus(post.owner.lastName)
            tvTitle.text = post.title
            tvDate.epochToDate(post.owner.postDate.toLong())
            tvComments.text = post.comments.toString().plus(" ")
                .plus(itemView.resources.getString(R.string.comments))
            tvLikes.text =
                post.likes.toString().plus(" ").plus(itemView.resources.getString(R.string.likes))
            Glide.with(itemView.context).load(post.owner.profile).placeholder(R.drawable.ic_launcher_background).into(ivProfile)

            post.images.let {
                if (it.isNotEmpty()) {
                    Glide.with(itemView.context).load(post.images[0]).placeholder(R.drawable.ic_launcher_background).into(ivFirstImage)
                }
                if (it.size > 1) {
                    Glide.with(itemView.context).load(post.images[1]).placeholder(R.drawable.ic_launcher_background).into(ivSecondImage)
                }
                if (it.size > 2) {
                    Glide.with(itemView.context).load(post.images[2]).placeholder(R.drawable.ic_launcher_background).into(ivThirdImage)
                }
            }
        }
    }

    fun setData(posts: List<PostPresentation>){
        submitList(posts)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(
            PostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind()
    }

    private class DiffCallback : DiffUtil.ItemCallback<PostPresentation>() {
        override fun areItemsTheSame(
            oldItem: PostPresentation,
            newItem: PostPresentation
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PostPresentation,
            newItem: PostPresentation
        ): Boolean {
            return oldItem == newItem
        }
    }
}