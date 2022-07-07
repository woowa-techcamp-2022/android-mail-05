package com.example.mailapp.data.source

import com.example.mailapp.data.Mail
import com.example.mailapp.data.MailType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class FakeMailDataSource : MailDataSource {
    override fun getMails(): Flow<List<Mail>> {
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
        return flow { emit(list) }
    }
}