package com.example.tbc_assign22.presentation.screen.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbc_assign22.R
import com.example.tbc_assign22.databinding.ImagesItemBinding

class ImagesRecyclerAdapter : RecyclerView.Adapter<ImagesRecyclerAdapter.ImagesViewHolder>() {

    private var images: List<String> = listOf()

    inner class ImagesViewHolder(private val binding: ImagesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val image = images[position]

            Glide.with(itemView.context).load(image).placeholder(R.drawable.ic_launcher_background)
                .into(binding.ivImage)

        }
    }

    fun setData(images: List<String>) {
        this.images = images
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        return ImagesViewHolder(
            ImagesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(position)
    }
}