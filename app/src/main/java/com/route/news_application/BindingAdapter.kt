package com.route.news_application

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

@BindingAdapter("image")
fun loadImageToImageView(image : ImageView , url : String){
    Glide.with(image)
        .load(url)
        .placeholder(R.drawable.background) //until loading
        .error(R.drawable.route) // if any error in loading
        .override(Target.SIZE_ORIGINAL) //  Specify image dimensions if necessary
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean,
            ): Boolean {
                Log.e("Glide","Error Loading image",e)
                return false
            }
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean,
            ): Boolean {
                return false
            }
        }) .into(image)
}