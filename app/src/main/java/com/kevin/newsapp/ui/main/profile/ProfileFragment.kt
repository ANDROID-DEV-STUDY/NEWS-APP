package com.kevin.newsapp.ui.main.profile

import com.kevin.newsapp.R
import com.kevin.newsapp.databinding.FragmentProfileBinding
import com.kevin.newsapp.ui.base.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    override val layoutResID: Int
        get() = R.layout.fragment_profile

    override val modelClass: Class<ProfileViewModel>
        get() = ProfileViewModel::class.java

    override fun onCreateView() {

    }

    companion object {
        val TAG = ProfileFragment::class.java.simpleName

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}
