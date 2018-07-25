package com.kevin.newsapp.util.databinding

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.kevin.newsapp.util.glide.RoundedCornersTransformation

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

@BindingAdapter("loadRoundedImageUrl")
fun ImageView.loadRoundedImageUrl(url: String?) {

    // TODO PlaceHolder
    url?.let {
        Glide.with(context)
                .load(it)
                .apply(RequestOptions.bitmapTransform(
                        MultiTransformation<Bitmap>(CenterCrop(), RoundedCornersTransformation())
                ))
                .into(this)
    }
}