package com.example.auto

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
    lateinit var name : TextView
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

    private fun updateCars() {
        name.text = viewModel.carName
        mileage.text = viewModel.carMileage
        mileage.text = viewModel.carMileage
        power.text = viewModel.carPower
        color.text = viewModel.carColor
        price.text = viewModel.carPrice
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnEdit = findViewById(R.id.btnEdit)
        btnAdd = findViewById(R.id.btnAdd)
        btnDelete = findViewById(R.id.btnDelete)
        btnPrev=findViewById(R.id.btnPrev)
        btnNext=findViewById(R.id.btnNext)
        name = findViewById(R.id.name)
        mileage = findViewById(R.id.mileage)
        color = findViewById(R.id.color)
        power = findViewById(R.id.power)
        price = findViewById(R.id.price)


        btnAdd.setOnClickListener{
            val intent = Intent(this, EditActivity::class.java)
            addCarLauncher.launch(intent)
        }

        btnEdit.setOnClickListener {
            if (viewModel.ListSize() != 0) {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("name", name.text.toString())
                intent.putExtra("mileage", mileage.text.toString())
                intent.putExtra("color", color.text.toString())
                intent.putExtra("power", power.text.toString())
                intent.putExtra("price", price.text.toString())
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