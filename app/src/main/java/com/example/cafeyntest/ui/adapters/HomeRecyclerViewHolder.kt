package com.example.cafeyntest.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.cafeyntest.databinding.ItemRecyclerHomeBinding
import com.example.cafeyntest.domains.ui.HomeRecyclerItem
import com.example.cafeyntest.ui.fragments.home.HomeFragmentVM

class HomeRecyclerViewHolder(private val binding: ItemRecyclerHomeBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(vm: HomeFragmentVM, item: HomeRecyclerItem) {
        binding.item = item
        binding.vm = vm
        binding.executePendingBindings()
    }

}