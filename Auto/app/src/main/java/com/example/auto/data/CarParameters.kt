package com.example.auto.data

import java.io.Serializable

data class CarParameters(
    val name: String,
    val mileage: String,
    val color: String,
    val power: String,
    val price: String,

): Serializable