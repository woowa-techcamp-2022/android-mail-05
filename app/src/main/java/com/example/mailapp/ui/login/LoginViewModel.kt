package com.example.mailapp.ui.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    val nickname: MutableLiveData<String> = MutableLiveData()
    val isValidNickname = nickname.map {
        it?.let { nickname ->
            checkNicknameValidation(nickname)
        }
    }

    val email: MutableLiveData<String> = MutableLiveData()
    val isValidEmail = email.map {
        it?.let { email ->
            checkEmailValidation(email)
        }
    }

    private fun checkNicknameValidation(nickname: String): Boolean {
        val engPattern = Pattern.compile(".*[a-zA-Z].*")
        val numPattern = Pattern.compile(".*[0-9].*")

        if (nickname.length < 4 || nickname.length > 12) return false
        return engPattern.matcher(nickname).matches() && numPattern.matcher(nickname).matches()
    }

    private fun checkEmailValidation(email: String): Boolean {
        val emailPattern = Patterns.EMAIL_ADDRESS

        return emailPattern.matcher(email).matches()
    }
}