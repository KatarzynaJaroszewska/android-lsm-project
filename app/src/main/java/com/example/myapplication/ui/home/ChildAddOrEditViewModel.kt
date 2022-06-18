package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.BaseViewModel
import java.util.*

class ChildAddOrEditViewModel : BaseViewModel() {
    private val _childName = MutableLiveData<String>().apply {
        value = ""
    }
    val childName: MutableLiveData<String> = _childName

    private val _buttonName = MutableLiveData<String>().apply {
        run {
            value = "ADD"
        }
    }

    val buttonName: LiveData<String> = _buttonName

    private val _birthdayDate = MutableLiveData<Date>().apply {
        value = Calendar.getInstance().time
    }
    val birthdayDate: MutableLiveData<Date> = _birthdayDate

}