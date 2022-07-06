package com.example.mailapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.mailapp.R
import com.example.mailapp.base.BaseFragment
import com.example.mailapp.databinding.FragmentLoginBinding
import com.example.mailapp.ui.HomeActivity

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val viewModel by activityViewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel

        viewModel.isValidNickname.observe(viewLifecycleOwner) {
            it?.let { isValidNickname ->
                if (isValidNickname.not()) {
                    binding.textInputLayoutNickname.error =
                        getString(R.string.nickname_validation_error_msg)
                } else {
                    binding.textInputLayoutNickname.error = null
                }
            }
        }

        viewModel.isValidEmail.observe(viewLifecycleOwner) {
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
            requireActivity().run {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }
    }
}