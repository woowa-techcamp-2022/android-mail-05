package com.example.mailapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.mailapp.R
import com.example.mailapp.databinding.ActivityMainBinding
import com.example.mailapp.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        binding.vm = viewModel

        viewModel.isValidNickname.observe(this) {
            it?.let { isValidNickname ->
                if (isValidNickname.not()) {
                    binding.textInputLayoutNickname.error =
                        getString(R.string.nickname_validation_error_msg)
                } else {
                    binding.textInputLayoutNickname.error = null
                }
            }
        }

        viewModel.isValidEmail.observe(this) {
            it?.let { isValidEmail ->
                if (isValidEmail.not()) {
                    binding.textInputLayoutEmail.error =
                        getString(R.string.email_validation_error_msg)
                } else {
                    binding.textInputLayoutEmail.error = null
                }
            }
        }

        binding.btnNext.setOnClickListener {
            startActivity(
                Intent(this, HomeActivity::class.java)
                    .putExtras(
                        bundleOf(
                            NICKNAME to viewModel.nickname.value,
                            EMAIL to viewModel.email.value
                        )
                    )
            )
            finish()
        }
    }

    companion object {
        const val NICKNAME = "NICKNAME"
        const val EMAIL = "EMAIL"
    }
}