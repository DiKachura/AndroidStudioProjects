package com.example.auto

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.auto.data.CarParameters
import com.example.auto.models.AutoModel

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    lateinit var btnEdit : Button
    lateinit var btnAdd : Button
    lateinit var btnDelete : Button
    lateinit var tvName : TextView
    lateinit var mileage : TextView
    lateinit var color : TextView
    lateinit var power : TextView
    lateinit var price : TextView
    lateinit var btnNext : ImageButton
    lateinit var btnPrev : ImageButton

    private val viewModel: AutoModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(AutoModel::class.java)
    }

    private val addCarLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val car = data?.getSerializableExtra("car") as? CarParameters
            car?.let {
                viewModel.addCar(it)
                updateCars()
            }
        }
    }

    private val editCarLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val car = data?.getSerializableExtra("car") as? CarParameters
            car?.let {
                viewModel.updateCar(it)
                updateCars()
            }
        }
    }

    @SuppressLint("SetTextI18n")
//    private fun updateCars() {
////        tvName.text = "Марка\n" + viewModel.carName + "\nПробег\n" + viewModel.carMileage + "\nЦвет\n" + viewModel.carColor+
////                "\nМощность\n" + viewModel.carPower + "\nЦена\n" + viewModel.carPrice
//        for(i in viewModel.carBank) {
//            tvName.text = viewModel.carBank.toString()
//        }
//    }

    private fun updateCars() {
        tvName.text = viewModel.toString()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnEdit = findViewById(R.id.btnEdit)
        btnAdd = findViewById(R.id.btnAdd)
        btnDelete = findViewById(R.id.btnDelete)
        btnPrev=findViewById(R.id.btnPrev)
        btnNext=findViewById(R.id.btnNext)
        tvName=findViewById(R.id.tvName)



        btnAdd.setOnClickListener{
            val intent = Intent(this, EditActivity::class.java)
            addCarLauncher.launch(intent)
        }

        btnEdit.setOnClickListener {
            if (viewModel.ListSize() != 0) {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("name", viewModel.carName)
                intent.putExtra("mileage", viewModel.carMileage)
                intent.putExtra("color", viewModel.carColor)
                intent.putExtra("power", viewModel.carPower)
                intent.putExtra("price", viewModel.carPrice)
                editCarLauncher.launch(intent)
            }

        }


        btnNext.setOnClickListener {
            viewModel.moveToNext()
            updateCars()
        }
        btnPrev.setOnClickListener {
            viewModel.moveToPrev()
            updateCars()
        }

        btnDelete.setOnClickListener {
            viewModel.deleteCar()
            updateCars()
        }

    }


}