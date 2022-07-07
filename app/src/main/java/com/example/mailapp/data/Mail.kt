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

fun getFakeMailData(): List<Mail> {
    val list = mutableListOf<Mail>()

    val typeList = listOf(MailType.PRIMARY, MailType.SOCIAL, MailType.PROMOTIONS)
    val contentList = listOf(
        "짧은 내용",
        "매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우매우 긴 메일내용"
    )

    repeat(20) {
        val sender = if (it % 2 == 0) "Google" else "우아한테크캠프"
        list.add(
            Mail(
                it,
                typeList[Random.nextInt(typeList.size)],
                sender + it,
                "제목",
                contentList[Random.nextInt(contentList.size)],
                "22.12.25"
            )
        )
    }

    return list
}