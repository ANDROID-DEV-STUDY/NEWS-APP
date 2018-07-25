package com.kevin.newsapp.util.extensions

import android.content.res.Resources

fun dpToPx(dp: Int):Int = (Resources.getSystem().displayMetrics.density * dp).toInt()