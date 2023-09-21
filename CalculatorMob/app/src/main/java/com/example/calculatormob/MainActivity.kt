package com.example.calculatormob

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var btn_0 : Button
    private lateinit var btn_1 : Button
    private lateinit var btn_2 : Button
    private lateinit var btn_3 : Button
    private lateinit var btn_4 : Button
    private lateinit var btn_5 : Button
    private lateinit var btn_6 : Button
    private lateinit var btn_7 : Button
    private lateinit var btn_8 : Button
    private lateinit var btn_9 : Button
    private lateinit var dot_btn : Button
    private lateinit var btn_mult : Button
    private lateinit var btn_minus : Button
    private lateinit var btn_plus : Button
    private lateinit var btn_divide : Button
    private lateinit var btn_clear : Button
    private lateinit var equal_btn : Button
    private lateinit var back : ImageButton
    private lateinit var input_1 : EditText
    private lateinit var input_2 : EditText
    private lateinit var answer : TextView
    private lateinit var symbol : TextView

    fun inputText(input: EditText){
        val cl = View.OnClickListener {
            val s = "${input.text.toString()}${(it as Button).text}"
            input.setText(s)
        }

        btn_0.setOnClickListener(cl)
        btn_1.setOnClickListener(cl)
        btn_2.setOnClickListener(cl)
        btn_3.setOnClickListener(cl)
        btn_4.setOnClickListener(cl)
        btn_5.setOnClickListener(cl)
        btn_6.setOnClickListener(cl)
        btn_7.setOnClickListener(cl)
        btn_8.setOnClickListener(cl)
        btn_9.setOnClickListener(cl)
        dot_btn.setOnClickListener(cl)

    }
    fun calculateResult(operation: String): Double{
        return when(operation){
            "+" -> input_1.text.toString().toDouble() + input_2.text.toString().toDouble()
            "-" -> input_1.text.toString().toDouble() - input_2.text.toString().toDouble()
            "*" -> input_1.text.toString().toDouble() * input_2.text.toString().toDouble()
            "/" -> input_1.text.toString().toDouble() / input_2.text.toString().toDouble()
            else -> {0.0}
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_0 = findViewById(R.id.btn_0)
        btn_1 = findViewById(R.id.btn_1)
        btn_2 = findViewById(R.id.btn_2)
        btn_3 = findViewById(R.id.btn_3)
        btn_4 = findViewById(R.id.btn_4)
        btn_5 = findViewById(R.id.btn_5)
        btn_6 = findViewById(R.id.btn_6)
        btn_7 = findViewById(R.id.btn_7)
        btn_8 = findViewById(R.id.btn_8)
        btn_9 = findViewById(R.id.btn_9)
        dot_btn = findViewById(R.id.dot_btn)
        btn_minus = findViewById(R.id.btn_minus)
        btn_plus = findViewById(R.id.btn_plus)
        btn_mult = findViewById(R.id.btn_mult)
        input_1 = findViewById(R.id.input_1)
        input_2 = findViewById(R.id.input_2)
        btn_divide = findViewById(R.id.btn_divide)
        btn_clear = findViewById(R.id.btn_clear)
        back = findViewById(R.id.back)
        answer = findViewById(R.id.answer)
        symbol = findViewById(R.id.symbol)
        equal_btn = findViewById(R.id.equal_btn)


        input_1.setOnClickListener {
            inputText(input_1)
        }

        input_2.setOnClickListener{
            inputText(input_2)
        }


        btn_clear.setOnClickListener {
            input_1.text.clear()
            input_2.text.clear()
            answer.text = ""
            symbol.text = ""
        }

        fun setTextFieldsSym(str: String) {
            symbol = findViewById(R.id.symbol)
            symbol.text = str
            input_2.requestFocus()
        }

        btn_plus.setOnClickListener{
            setTextFieldsSym("+")
        }
        btn_minus.setOnClickListener{
            setTextFieldsSym("-")
        }
        btn_mult.setOnClickListener{
            setTextFieldsSym("*")
        }
        btn_divide.setOnClickListener{
            setTextFieldsSym("/")
        }

        back.setOnClickListener {
            var str: String = input_1.getText().toString().trim()

            if (str.length != 0) {
                str = str.substring(0, str.length - 1)
                input_1.setText(str)
            }
        }

        equal_btn.setOnClickListener{
            answer.text = calculateResult(symbol.text.toString()).toString()
        }


    }
}