package com.example.cafeyntest.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.cafeyntest.databinding.ItemRecyclerHomeBinding
import com.example.cafeyntest.domains.ui.HomeRecyclerItem

class HomeRecyclerViewHolder(private val binding: ItemRecyclerHomeBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HomeRecyclerItem) {
        binding.item = item
        binding.executePendingBindings()
    }

}