package com.example.cafeyntest.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cafeyntest.domains.ui.HomeRecyclerItem
import com.example.cafeyntest.ui.adapters.HomeRecyclerAdapter

@BindingAdapter("home_items")
fun setHomeRecyclerItems(view: RecyclerView, items: Collection<HomeRecyclerItem>?) {
    (view.adapter as? HomeRecyclerAdapter)?.let { adapter ->
        if (items != null)
            adapter.setItems(items)
    }
}

@BindingAdapter("image_url")
fun setImageSrc(view: ImageView, url: String) {
    Glide.with(view).load(url).into(view)
}