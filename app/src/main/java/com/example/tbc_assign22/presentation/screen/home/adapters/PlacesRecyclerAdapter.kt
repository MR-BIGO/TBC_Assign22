package com.example.tbc_assign22.presentation.screen.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbc_assign22.R
import com.example.tbc_assign22.databinding.PlaceItemBinding
import com.example.tbc_assign22.presentation.model.PlacePresentation

class PlacesRecyclerAdapter :
    ListAdapter<PlacePresentation, PlacesRecyclerAdapter.PlacesViewHolder>(DiffCallback()) {

    inner class PlacesViewHolder(private val binding: PlaceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            val place = currentList[adapterPosition]
            tvTitle.text = place.title
            Glide.with(itemView.context).load(place.cover).placeholder(R.drawable.ic_launcher_background).into(ivPlace)
        }
    }

    fun setData(places: List<PlacePresentation>) {
        submitList(places)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        return PlacesViewHolder(
            PlaceItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        holder.bind()
    }

    private class DiffCallback : DiffUtil.ItemCallback<PlacePresentation>() {
        override fun areItemsTheSame(
            oldItem: PlacePresentation,
            newItem: PlacePresentation
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PlacePresentation,
            newItem: PlacePresentation
        ): Boolean {
            return oldItem == newItem
        }
    }
}