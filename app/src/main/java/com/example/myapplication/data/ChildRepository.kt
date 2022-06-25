package com.example.myapplication.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChildRepository @Inject constructor(private val childDao: ChildDao){

    fun insert(child: Child) = childDao.insert(child)

    fun getChildren() = childDao.getChildren()

}