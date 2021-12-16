package com.example.cafeyntest.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeyntest.databinding.ItemRecyclerHomeBinding
import com.example.cafeyntest.domains.ui.HomeRecyclerItem
import com.example.cafeyntest.ui.fragments.home.HomeFragmentVM

class HomeRecyclerAdapter : RecyclerView.Adapter<HomeRecyclerViewHolder>() {

    private val itemsList = ArrayList<HomeRecyclerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder =
        HomeRecyclerViewHolder(
            ItemRecyclerHomeBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) =
        holder.bind(
            ViewModelProviders.of(holder.itemView.context as FragmentActivity)[HomeFragmentVM::class.java],
            itemsList[position]
        )

    override fun getItemCount() = itemsList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: Collection<HomeRecyclerItem>?) {
        if (itemsList.isNotEmpty())
            itemsList.clear()

        if (items != null && items.isNotEmpty())
            itemsList.addAll(items)

        notifyDataSetChanged()
    }

}