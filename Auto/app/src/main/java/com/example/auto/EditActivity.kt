package com.example.auto

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.auto.data.CarParameters

class EditActivity : AppCompatActivity() {
    lateinit var btnSave : Button
    lateinit var name : EditText
    lateinit var mileage : EditText
    lateinit var color : EditText
    lateinit var power : EditText
    lateinit var price : EditText

    private fun validate_fields(): Boolean {
        var flag = true
        if (name.text.isNullOrBlank()) {
            name.error = "Введите марку автомобиля"
            flag = false
        }
        else if (mileage.text.isNullOrBlank()) {
            mileage.error = "Введите пробег автомобиля"
            flag = false
        }
        else if (mileage.text.length > 2 && mileage.text.toString()[0] == '0' && mileage.text.toString()[1] != '.') {
            mileage.error = "Не может начинаться с нуля"
            flag = false
        }
        else if (mileage.text.toString() == "0"){
            flag = true

        }
        else if (color.text.isNullOrBlank()) {
            color.error = "Введите цвет автомобиля"
            flag = false
        }
        else if (power.text.isNullOrBlank()) {
            power.error = "Введите мощность автомобиля"
            flag = false
        }
        else if (power.text.toString() == "0"){
            flag = true

        } else if (power.text.length > 2 && power.text.toString()[0] == '0' && power.text.toString()[1] != '.'){
            power.error = "Не может начинаться с нуля"
            flag = false
        }
        else if (price.text.isNullOrBlank()) {
            price.error = "Введите цену автомобиля"
            flag = false
        }
        else if (price.text.length > 2 && price.text.toString()[0] == '0' && price.text.toString()[1] != '.') {
            price.error = "Не может начинаться с нуля"
            flag = false
        }
        else if (price.text.toString() == "0"){
            flag = true

        }
        return flag
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        name = findViewById(R.id.name)
        mileage = findViewById(R.id.mileage)
        color = findViewById(R.id.color)
        power = findViewById(R.id.power)
        price = findViewById(R.id.price)
        btnSave = findViewById(R.id.btnSave)


        name.setText(intent.getStringExtra("name") ?: "")
        mileage.setText(intent.getStringExtra("mileage") ?: "")
        color.setText(intent.getStringExtra("color") ?: "")
        power.setText(intent.getStringExtra("power") ?: "")
        price.setText(intent.getStringExtra("price") ?: "")





        btnSave.setOnClickListener {
            if (validate_fields()) {
                val car = CarParameters(
                    name.text.toString(),
                    mileage.text.toString(),
                    color.text.toString(),
                    power.text.toString(),
                    price.text.toString()
                )
                val intent = Intent()
                intent.putExtra("car", car)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

    }
}