package com.example.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Child
import com.example.myapplication.data.ChildRepository
import com.example.myapplication.ui.BaseViewModel
import com.example.myapplication.ui.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChildAddOrEditViewModel @Inject constructor(private val childRepository: ChildRepository) : BaseViewModel() {

    val showDatePickerRequest = SingleLiveEvent<Date>()

    private val _child = MutableLiveData<Child>()
    var child = _child

    override fun prepare(args: Bundle?) {
        super.prepare(args)
        _child.value = Child(name = "Child Name", behaviorPoints = 0, dutyPoints = 0, drawableName = "",
            birthday = Calendar.getInstance().time)
    }

    fun saveAddChildOrEdit() {
        viewModelScope.launch(Dispatchers.IO) {
            val childToAdd = child.value
            if (childToAdd != null)
                childRepository.insert(childToAdd)
        }
    }

    fun showDatePicker() {
        child.value?.let {
            showDatePickerRequest.postValue(it.birthday)
        }
    }

    private val _buttonName = MutableLiveData<String>().apply {
        run {
            value = "ADD"
        }
    }

    val buttonName: LiveData<String> = _buttonName

}