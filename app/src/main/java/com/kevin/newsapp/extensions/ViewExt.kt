package com.kevin.newsapp.extensions

import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(url : String)
        = Glide
        .with(this)
        .load(url)
        .apply(RequestOptions.centerCropTransform())
        .into(this)

fun ViewGroup.inflate(resId : Int)
    = this.inflate(resId, false)

fun ViewGroup.inflate(resId : Int, attachToRoot : Boolean)
        = LayoutInflater.from(this.context).inflate(resId, this, attachToRoot)

fun View.snackbar(message : String)
    = Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()