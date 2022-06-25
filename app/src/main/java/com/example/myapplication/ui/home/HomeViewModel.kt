package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Child
import com.example.myapplication.data.ChildRepository
import com.example.myapplication.ui.BaseViewModel
import com.example.myapplication.ui.NavigationCommand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val childRepository: ChildRepository) : BaseViewModel() {

    private val _childList = MutableLiveData<List<Child>>()
    val childList : LiveData<List<Child>> = _childList

    init {
        fetchChildList()
    }

    private fun fetchChildList() {
        viewModelScope.launch {
            childRepository.getChildren().collect{
                _childList.postValue(it)
            }
        }
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