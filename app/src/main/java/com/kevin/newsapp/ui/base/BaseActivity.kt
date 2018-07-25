package com.kevin.newsapp.ui.base

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.kevin.newsapp.util.extensions.plusAssign
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity<DB: ViewDataBinding, VM: BaseViewModel>: AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract val layoutResID: Int

    lateinit var mBinding: DB

    lateinit var mViewModel: VM

    abstract val modelClass: Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        mBinding = (DataBindingUtil.setContentView(this, layoutResID) as DB)
                .apply { setLifecycleOwner(this@BaseActivity) }

        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(modelClass)

        lifecycle += mViewModel
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector
}