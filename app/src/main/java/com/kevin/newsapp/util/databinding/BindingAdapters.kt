package com.kevin.newsapp.util.databinding

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.kevin.newsapp.util.extensions.gone
import com.kevin.newsapp.util.glide.RoundedCornersTransformation
import java.util.*

@BindingAdapter("setImageUrl")
fun ImageView.setImageUrl(url: String?){
    url?.let {
        Glide.with(context)
                .load(it)
                .apply(RequestOptions.centerCropTransform())
                .listener(object: RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        gone()
                        return true
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false
                    }
                })
                .into(this)
    }
    ?:gone()
}

@BindingAdapter("setRoundedImageUrl")
fun ImageView.setRoundedImageUrl(url: String?) {
    url?.let {
        Glide.with(context)
                .load(it)
                .apply(RequestOptions.bitmapTransform(
                        MultiTransformation<Bitmap>(CenterCrop(), RoundedCornersTransformation())
                ))
                .into(this)
    }
}

@BindingAdapter("setDate")
fun TextView.setDate(date: Date) {
    val calendar = Calendar.getInstance().apply { time = date }
    val actualMonth = calendar.get(Calendar.MONTH) + 1
    val date = calendar.get(Calendar.DATE)

    text = "${actualMonth}월 ${date}일"
}