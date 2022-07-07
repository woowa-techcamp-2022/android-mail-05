package com.example.mailapp.data

import kotlin.random.Random

data class Mail(
    val id: Int,
    val type: MailType,
    val sender: String,
    val title: String,
    val content: String,
    val date: String
)