package com.example.cafeyntest.bindings

import android.graphics.Bitmap
import android.util.Patterns
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.cafeyntest.domains.ui.HomeRecyclerItem
import com.example.cafeyntest.storage.ImagePersistenceManager
import com.example.cafeyntest.ui.adapters.HomeRecyclerAdapter

@BindingAdapter("items")
fun setHomeRecyclerItems(view: RecyclerView, items: List<HomeRecyclerItem>?) {
    (view.adapter as? HomeRecyclerAdapter)?.let { adapter ->
        if (items != null)
            adapter.setItems(items)
    }
}

@BindingAdapter("image_url", "placeholder", requireAll = true)
fun setImageSrc(view: ImageView, url: String?, @DrawableRes placeholderId: Int) {
    if (url.isNullOrEmpty()) {
        view.setImageResource(placeholderId)
        return
    }

    val completeUrl =
        if (Patterns.WEB_URL.matcher(url).matches() && !url.substringAfterLast('/').contains('.')) {
            "$url.png"
        } else {
            url
        }

    ImagePersistenceManager.loadImage(completeUrl)?.let {
        view.setImageBitmap(it)
        return
    }

    Glide.with(view)
        .asBitmap()
        .placeholder(placeholderId)
        .listener(getGlideBitmapRequestListener(completeUrl))
        .load(completeUrl)
        .into(view)
}

private fun getGlideBitmapRequestListener(imageUrl: String) =
    object : RequestListener<Bitmap> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Bitmap>?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

        override fun onResourceReady(
            resource: Bitmap?,
            model: Any?,
            target: Target<Bitmap>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            if (resource != null) {
                ImagePersistenceManager.saveImage(imageUrl, resource)
            }
            return false
        }
    }