package com.example.mailapp.ui.mail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.mailapp.data.Mail
import com.example.mailapp.databinding.ItemMailBinding

class MailViewHolder(val binding: ItemMailBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Mail) {
        binding.mail = item
    }
}