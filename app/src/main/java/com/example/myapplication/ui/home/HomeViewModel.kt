package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.BaseViewModel
import com.example.myapplication.ui.NavigationCommand

class HomeViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun navigateToAddChild() {
        navigateTo(NavigationCommand.To(HomeFragmentDirections.actionNavigationHomeToChildAddOrEditFragment()))
    }
}