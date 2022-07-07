package com.example.mailapp.ui.home

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.commit
import com.example.mailapp.R
import com.example.mailapp.data.MailType
import com.example.mailapp.data.UserData
import com.example.mailapp.databinding.ActivityHomeBinding
import com.example.mailapp.ui.login.LoginActivity.Companion.EMAIL
import com.example.mailapp.ui.login.LoginActivity.Companion.NICKNAME
import com.example.mailapp.ui.mail.MailFragment
import com.example.mailapp.ui.mail.MailViewModel
import com.example.mailapp.ui.setting.SettingFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModels<MailViewModel>()

    @Inject
    lateinit var userData: UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        userData.setUserData(
            nickname = intent.getStringExtra(NICKNAME),
            email = intent.getStringExtra(EMAIL)
        )

        setUpActionBar()
        setUpBottomNavigation()
        setUpNavigationRail()
        setUpNavigationView()
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.run {
            title = getString(R.string.mail_title)
            setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }

    private fun setUpBottomNavigation() {
        binding.bottomNavigationHome?.setOnItemSelectedListener {
            navigateToFragment(it.itemId)
        }
        binding.bottomNavigationHome?.selectedItemId = R.id.action_mail
    }

    private fun setUpNavigationRail() {
        binding.navigationRailHome?.setOnItemSelectedListener {
            navigateToFragment(it.itemId)
        }
        binding.bottomNavigationHome?.selectedItemId = R.id.action_mail
    }

    private fun setUpNavigationView() {
        binding.navigationViewHome.run {
            setNavigationItemSelectedListener { item ->
                MailType.getMailTypeById(item.itemId)?.let {
                    viewModel.selectMailType(it)
                    binding.drawerHome.close()
                    return@setNavigationItemSelectedListener true
                }
                false
            }
        }

        viewModel.selectedMailType.observe(this) {
            it?.let { t ->
                binding.navigationViewHome.setCheckedItem(t.menuId)
                binding.bottomNavigationHome?.selectedItemId = R.id.action_mail
            }
        }
    }

    private fun navigateToFragment(@IdRes id: Int): Boolean {
        when (id) {
            R.id.action_mail -> supportFragmentManager.commit {
                replace(R.id.fragment_container_home, MailFragment())
            }
            R.id.action_setting -> supportFragmentManager.commit {
                viewModel.selectMailType(MailType.PRIMARY)
                replace(R.id.fragment_container_home, SettingFragment())
                addToBackStack(null)
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                binding.drawerHome.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)

        binding.bottomNavigationHome?.let {
            savedInstanceState.putInt(
                SELECTED_ITEM_ID,
                it.selectedItemId
            )
        }
        binding.navigationRailHome?.let {
            savedInstanceState.putInt(
                SELECTED_ITEM_ID,
                it.selectedItemId
            )
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val selectedItemId = savedInstanceState.getInt(SELECTED_ITEM_ID)
        binding.bottomNavigationHome?.selectedItemId = selectedItemId
        binding.navigationRailHome?.selectedItemId = selectedItemId
    }

    companion object {
        const val SELECTED_ITEM_ID = "SELECTED_ITEM_ID"
    }
}