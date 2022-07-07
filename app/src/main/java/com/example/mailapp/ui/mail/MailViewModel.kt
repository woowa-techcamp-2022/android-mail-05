package com.example.mailapp.ui.mail

import androidx.lifecycle.*
import com.example.mailapp.data.MailType
import com.example.mailapp.data.source.MailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MailViewModel @Inject constructor(
    private val mailRepository: MailRepository
) : ViewModel() {
    private val _selectedMailType: MutableLiveData<MailType> = MutableLiveData(MailType.PRIMARY)
    val selectedMailType: LiveData<MailType> get() = _selectedMailType

    val mail = mailRepository.mailList.asLiveData()

    val filteredMail = selectedMailType.switchMap { type ->
        mail.map {
            it.filter { m -> m.type == type }
        }
    }

    fun selectMailType(type: MailType) {
        _selectedMailType.value = type
    }
}