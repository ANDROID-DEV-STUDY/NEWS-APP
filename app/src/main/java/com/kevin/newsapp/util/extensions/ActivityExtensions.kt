package com.kevin.newsapp.util.extensions

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.replaceFragment(@IdRes containerViewId: Int, fragment: Fragment, tag: String)
    = supportFragmentManager
            .beginTransaction()
            .replace(containerViewId, fragment, tag)
            .commit()