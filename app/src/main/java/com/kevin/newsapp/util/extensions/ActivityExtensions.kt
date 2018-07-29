package com.kevin.newsapp.util.extensions

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.replaceFragment(@IdRes containerViewId: Int, fragment: Fragment, tag: String)
        = transaction { replace(containerViewId, fragment, tag) }

fun AppCompatActivity.addFragment(@IdRes containerViewId: Int, fragment: Fragment, tag: String)
        = transaction { add(containerViewId, fragment, tag) }

fun AppCompatActivity.showFragment(fragment: Fragment) = transaction { show(fragment) }

fun AppCompatActivity.hideFragment(fragment: Fragment) = transaction { hide(fragment) }

fun AppCompatActivity.hideFragmentByTag(tag: String) = transaction { hide(supportFragmentManager.findFragmentByTag(tag)) }

fun AppCompatActivity.transaction(transaction: FragmentTransaction.() -> FragmentTransaction) {
    supportFragmentManager
            .beginTransaction()
            .transaction()
            .commit()

    supportFragmentManager.executePendingTransactions()
}