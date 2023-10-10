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
            if (mileage.text.toString()[0] == '0' && mileage.text.toString()[1] != '.')
                mileage.error = "Не может начинаться с нуля"
            else if (power.text.toString()[0] == '0' && power.text.toString()[1] != '.')
                power.error = "Не может начинаться с нуля"
            else if (price.text.toString()[0] == '0' && price.text.toString()[1] != '.')
                price.error = "Не может начинаться с нуля"
            else if(name.text.toString()!="" && mileage.text.toString()!="" && color.text.toString()!="" && power.text.toString()!="" && price.text.toString()!="") {
                val car = CarParameters(
                    name.text.toString(),
                    mileage.text.toString(),
                    color.text.toString(),
                    power.text.toString(),
                    price.text.toString()
                )


                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("car", car)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

            else if(name.text.toString()=="") name.error = "Введите марку автомобиля!"
            else if(mileage.text.toString()=="") mileage.error = "Введите пробег автомобиля!"
            else if(color.text.toString()=="") color.error = "Введите цвет автомобиля!"
            else if(power.text.toString()=="") power.error = "Введите мощность автомобиля!"
            else if(price.text.toString()=="") price.error = "Введите цену автомобиля!"

        }

    }
}