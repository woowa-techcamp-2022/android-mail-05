package com.example.mailapp.ui.mail

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import com.example.mailapp.R
import com.example.mailapp.base.BaseFragment
import com.example.mailapp.data.MailType
import com.example.mailapp.databinding.FragmentMailBinding
import com.example.mailapp.ui.mail.adapter.MailAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MailFragment : BaseFragment<FragmentMailBinding>(R.layout.fragment_mail) {
    private val viewModel by activityViewModels<MailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (viewModel.selectedMailType.value != MailType.PRIMARY) {
                        viewModel.selectMailType(MailType.PRIMARY)
                    } else {
                        requireActivity().finish()
                    }
                }

            }
        )

        binding.vm = viewModel

        val mailAdapter = MailAdapter()
        binding.rvMail.run {
            setHasFixedSize(true)
            adapter = mailAdapter
        }

        viewModel.filteredMail.observe(viewLifecycleOwner) {
            mailAdapter.submitList(it)
        }
    }
}