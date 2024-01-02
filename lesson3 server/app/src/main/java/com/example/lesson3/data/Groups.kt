package com.example.lesson3.data

import com.google.gson.annotations.SerializedName

class Groups {
    @SerializedName("items") lateinit var items: List<Group>
}