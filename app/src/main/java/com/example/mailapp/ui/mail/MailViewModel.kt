package com.example.mailapp.ui.mail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.mailapp.data.Mail
import com.example.mailapp.data.MailType
import com.example.mailapp.data.getFakeMailData

class MailViewModel : ViewModel() {
    private val _selectedMailType: MutableLiveData<MailType> = MutableLiveData(MailType.PRIMARY)
    val selectedMailType: LiveData<MailType> get() = _selectedMailType

    private val _mail: MutableLiveData<List<Mail>> = MutableLiveData()
    val mail: LiveData<List<Mail>> get() = _mail

    val filteredMail = selectedMailType.map { type ->
        _mail.value?.filter { it.type == type }
    }

    init {
        _mail.value = getFakeMailData()
    }

    fun selectMailType(type: MailType) {
        _selectedMailType.value = type
    }
}