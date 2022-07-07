package com.example.mailapp.util

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.databinding.BindingAdapter
import com.example.mailapp.R
import com.google.android.material.textview.MaterialTextView
import java.util.regex.Pattern
import kotlin.random.Random

@BindingAdapter("senderName")
fun setSenderName(tv: MaterialTextView, senderName: String) {
    val engPattern = Pattern.compile(".*[a-zA-Z].*")
    val startStr = senderName[0].toString()
    val color: Int = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

    if (engPattern.matcher(startStr).matches()) {
        tv.text = startStr
        tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
    } else {
        tv.text = ""
        tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.person_icon, 0, 0, 0)
    }
    tv.backgroundTintList = ColorStateList.valueOf(color)
}