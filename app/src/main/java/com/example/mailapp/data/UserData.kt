package com.example.mailapp.data

import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UserData @Inject constructor() {
    var nickname: String? = null
        private set
    var email: String? = null
        private set

    fun setUserData(nickname: String?, email: String?) {
        this.nickname = nickname
        this.email = email
    }
}