package com.example.mailapp.ui

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.mailapp.R
import com.example.mailapp.databinding.ActivityHomeBinding
import com.example.mailapp.ui.mail.MailFragment
import com.example.mailapp.ui.setting.SettingFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationHome?.setOnItemSelectedListener {
            navigateToFragment(it.itemId)
        }

        binding.bottomNavigationRailHome?.setOnItemSelectedListener {
            navigateToFragment(it.itemId)
        }
    }

    private fun navigateToFragment(@IdRes id: Int): Boolean {
        when (id) {
            R.id.action_mail -> supportFragmentManager.commit {
                replace(R.id.fragment_container_home, MailFragment())
            }
            R.id.action_setting -> supportFragmentManager.commit {
                replace(R.id.fragment_container_home, SettingFragment())
            }
        }
        return true
    }
}