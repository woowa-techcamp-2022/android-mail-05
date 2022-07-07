package com.example.mailapp.ui.mail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.mailapp.R
import com.example.mailapp.data.Mail
import com.example.mailapp.databinding.ItemMailBinding

class MailAdapter : ListAdapter<Mail, MailViewHolder>(MailDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailViewHolder {
        val binding = DataBindingUtil.inflate<ItemMailBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_mail,
            parent,
            false
        )
        return MailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class MailDiffCallback : DiffUtil.ItemCallback<Mail>() {
    override fun areItemsTheSame(
        oldItem: Mail, newItem: Mail
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Mail, newItem: Mail
    ): Boolean {
        return oldItem == newItem
    }
}