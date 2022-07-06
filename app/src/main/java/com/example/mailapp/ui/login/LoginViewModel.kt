package com.example.mailapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    val nickname: MutableLiveData<String> = MutableLiveData()
    val isValidNickname = nickname.map {
        it?.let { nickname ->
            checkNicknameValidation(nickname)
        }
    }

    private fun checkNicknameValidation(nickname: String): Boolean {
        val engPattern = Pattern.compile(".*[a-zA-Z].*")
        val numPattern = Pattern.compile(".*[0-9].*")

        if (nickname.length < 4 || nickname.length > 12) return false
        return engPattern.matcher(nickname).matches() && numPattern.matcher(nickname).matches()
    }
}