package com.example.lesson3.API

import com.example.lesson3.data.Student
import com.google.gson.annotations.SerializedName

class PostStudent (
    @SerializedName("action") val action:Int,
    @SerializedName("student") val student: Student
)