package com.example.mailapp.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.example.mailapp.R
import com.example.mailapp.base.BaseFragment
import com.example.mailapp.databinding.FragmentLoginBinding
import com.example.mailapp.ui.mail.MailFragment

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

        binding.btnNext.setOnClickListener {
            navigateToMailFragment()
        }
    }

    private fun navigateToMailFragment() {
        parentFragmentManager.commit {
            replace(R.id.fragment_container_main, MailFragment())
        }
    }
}