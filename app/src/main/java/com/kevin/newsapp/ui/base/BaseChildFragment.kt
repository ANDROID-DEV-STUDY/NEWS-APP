package com.kevin.newsapp.ui.base

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kevin.newsapp.di.qualifier.ChildFragmentLevel
import com.kevin.newsapp.util.extensions.plusAssign
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseChildFragment<DB: ViewDataBinding, VM: BaseViewModel>: Fragment() {

    abstract val layoutResID: Int

    lateinit var mContext: Context

    lateinit var mBinding: DB

    @Inject
    @field:ChildFragmentLevel
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var mViewModel: VM

    abstract val modelClass: Class<VM>

    val mDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutResID, container, false)

        mBinding.setLifecycleOwner(this)

        onCreateView()

        return mBinding.root
    }

    abstract fun onCreateView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(modelClass)

        lifecycle += mViewModel
    }
}