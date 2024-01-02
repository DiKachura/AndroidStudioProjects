package com.example.lesson3.data

import com.google.gson.annotations.SerializedName

class Students {
    @SerializedName("items") lateinit var items: List<Student>
}