package com.kevin.newsapp.util.extensions

import android.app.Activity
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

// VISIBILITY
fun View.visible() { visibility = View.VISIBLE }

fun View.invisible() { visibility = View.INVISIBLE }

fun View.gone() { visibility = View.GONE }

fun View.onClick(func: () -> Unit) = setOnClickListener { func() }

// GLIDE
fun ImageView.loadImage(url : String)
        = Glide.with(this).load(url).apply(RequestOptions.centerCropTransform()).into(this)

// INFLATER
fun ViewGroup.inflate(resId : Int) = inflate(resId, false)

fun ViewGroup.inflate(resId : Int, attachToRoot : Boolean)
        = LayoutInflater.from(this.context).inflate(resId, this, attachToRoot)

// SNACK BAR
fun View.snackbar(message : String) = Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()

// CONSTRAINT LAYOUT
fun ConstraintLayout.update(constraintSet: ConstraintSet = ConstraintSet(), update: ConstraintSet.() -> Unit){
    constraintSet.clone(this)
    constraintSet.update()
    constraintSet.applyTo(this)
}

// KEYBOARD
fun View.showKeyboard()
    = (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(this, 0)


fun View.hideKeyboard()
    = (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromInputMethod(windowToken, 0)

fun setUpTabPager(tabLayout: TabLayout, viewPager: ViewPager) {
    viewPager.addOnPageChangeListener(android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener(tabLayout))

    tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {}

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabSelected(tab: TabLayout.Tab) { viewPager.currentItem = tab.position }
    })
}