package com.example.auto.models

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.auto.data.CarParameters

class AutoModel: ViewModel() {
    //val carBank: MutableList<CarParameters> = mutableListOf()
    val carBank = mutableListOf<CarParameters>()

    var currentIndex=-1

    val carName:String
        get() = if (currentIndex >= 0 && currentIndex < carBank.size) {
            carBank[currentIndex].name
        } else {
            ""
        }
    val carMileage:String
        get() = if (currentIndex >= 0 && currentIndex < carBank.size) {
            carBank[currentIndex].mileage
        } else {
            ""
        }

    val carColor:String
        get() = if (currentIndex >= 0 && currentIndex < carBank.size) {
            carBank[currentIndex].color
        } else {
            ""
        }

    val carPower:String
        get() = if (currentIndex >= 0 && currentIndex < carBank.size) {
            carBank[currentIndex].power
        } else {
            ""
        }

    val carPrice:String
        get() = if (currentIndex >= 0 && currentIndex < carBank.size) {
            carBank[currentIndex].price
        } else {
            ""
        }


    fun moveToNext() {
        if (carBank.isNotEmpty()) {
            currentIndex = (currentIndex + 1) % carBank.size
        }
    }
    fun ListSize(): Int {
        return carBank.size
    }
    fun moveToPrev() {
        if (carBank.isNotEmpty()) {
            currentIndex = (carBank.size + currentIndex - 1) % carBank.size
        }
    }


    fun addCar(car: CarParameters) {
        carBank.add(car)
        if (carBank.isEmpty()) {
            carBank.clear()
            currentIndex = 0
        } else {
            currentIndex++
        }
        Log.d("AutoModel", "carBank: $carBank")
    }

    fun updateCar(car: CarParameters) {
        if (currentIndex >= 0 && currentIndex < carBank.size) {
            carBank[currentIndex] = car
        }
    }

    fun deleteCar() {
        if (currentIndex >= 0 && currentIndex < carBank.size) {
            carBank.removeAt(currentIndex)
            if (carBank.isEmpty()) {
                currentIndex = -1
            } else {
                currentIndex %= carBank.size
            }
        }
    }
    override fun toString(): String {
        return "Марка\n$carName\nПробег\n$carMileage\nЦвет\n$carColor\nМощность\n$carPower\nЦена\n$carPrice"
    }
}