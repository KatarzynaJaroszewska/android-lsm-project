package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Child (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var name: String,
    var behaviorPoints: Int = 0,
    var dutyPoints: Int = 0,
    var drawableName: String = "",
    var birthday: Date
)