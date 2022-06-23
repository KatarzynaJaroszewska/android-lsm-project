package com.example.myapplication.data

import java.util.*

data class Child (
    var name: String,
    var behaviorPoints: Int = 0,
    var dutyPoints: Int = 0,
    var drawableName: String = "",
    var birthday: Date
)