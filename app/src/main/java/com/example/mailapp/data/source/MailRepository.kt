package com.example.mailapp.data.source

class MailRepository(mailDataSource: MailDataSource) {
    val mailList = mailDataSource.getMails()
}