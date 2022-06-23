package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Child
import com.example.myapplication.ui.BaseViewModel
import com.example.myapplication.ui.NavigationCommand
import java.util.*

class HomeViewModel : BaseViewModel() {

    private val _childList = MutableLiveData<List<Child>>()
    val childList : LiveData<List<Child>> = _childList

    init {
        fetchChildList()
    }

    private fun fetchChildList() {
        var newList = listOf(
            Child(name = "Andrzej", dutyPoints = 10, behaviorPoints = 15, drawableName = "child_1_icon", birthday = Calendar.getInstance().time),
            Child(name = "Monika", dutyPoints = 15, behaviorPoints = 5, drawableName = "child_2_icon", birthday = Calendar.getInstance().time),
            Child(name = "Marcin", dutyPoints = 33, behaviorPoints = 70, drawableName = "child_3_icon", birthday = Calendar.getInstance().time)
        )

        _childList.value = newList
    }

    private val _buttonName = MutableLiveData<String>().apply {
        run {
            value = "ADD CHILD"
        }
    }
    val buttonName: LiveData<String> = _buttonName

    fun navigateToAddChild() {
        navigateTo(NavigationCommand.To(HomeFragmentDirections.actionNavigationHomeToChildAddOrEditFragment()))
    }
}