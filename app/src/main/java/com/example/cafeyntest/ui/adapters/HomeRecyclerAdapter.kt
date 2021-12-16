package com.example.cafeyntest.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeyntest.databinding.ItemRecyclerHomeBinding
import com.example.cafeyntest.domains.ui.HomeRecyclerItem

class HomeRecyclerAdapter : RecyclerView.Adapter<HomeRecyclerViewHolder>() {

    private val itemsList = ArrayList<HomeRecyclerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeRecyclerViewHolder(
            ItemRecyclerHomeBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) = holder.bind(itemsList[position])

    override fun getItemCount() = itemsList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: Collection<HomeRecyclerItem>) {
        if (itemsList.isNotEmpty())
            itemsList.clear()

        if (items.isNotEmpty())
            itemsList.addAll(items)

        notifyDataSetChanged()
    }

}