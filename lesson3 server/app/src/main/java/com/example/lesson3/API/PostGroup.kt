package com.example.lesson3.API

import com.example.lesson3.data.Group
import com.google.gson.annotations.SerializedName

class PostGroup (
    @SerializedName("action") val action:Int,
    @SerializedName("group") val group: Group
)