package com.example.mailapp.data.source

import com.example.mailapp.data.Mail
import kotlinx.coroutines.flow.Flow

interface MailDataSource {
    fun getMails(): Flow<List<Mail>>
}