package com.example.mailapp.ui.setting

import android.os.Bundle
import android.view.View
import com.example.mailapp.R
import com.example.mailapp.base.BaseFragment
import com.example.mailapp.data.UserData
import com.example.mailapp.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    @Inject
    lateinit var userData: UserData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userData = userData
    }

    companion object {
        const val TAG = "SettingFragment"
    }
}