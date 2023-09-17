package com.example.calculatormob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var btn_0: TextView
    lateinit var btn_1: TextView
    lateinit var btn_2: TextView
    lateinit var btn_3: TextView
    lateinit var btn_4: TextView
    lateinit var btn_5: TextView
    lateinit var btn_6: TextView
    lateinit var btn_7: TextView
    lateinit var btn_8: TextView
    lateinit var btn_9: TextView
    lateinit var minus_btn: TextView
    lateinit var plus_btn: TextView
    lateinit var mult_btn: TextView
    lateinit var division_btn: TextView
    lateinit var clear_btn: TextView
    lateinit var math_operation: TextView
    lateinit var result_text: TextView
    lateinit var dot_btn: TextView
    lateinit var back_btn: TextView
    lateinit var symbol_text: TextView
    lateinit var equal_btn: TextView

    lateinit var previousOperation: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_0 = findViewById(R.id.btn_0)
        btn_0.setOnClickListener {
            setTextFieldsNum("0")
        }

        btn_1 = findViewById(R.id.btn_1)
        btn_1.setOnClickListener {
            setTextFieldsNum("1")
        }

        btn_2 = findViewById(R.id.btn_2)
        btn_2.setOnClickListener {
            setTextFieldsNum("2")
        }

        btn_3 = findViewById(R.id.btn_3)
        btn_3.setOnClickListener {
            setTextFieldsNum("3")
        }

        btn_4 = findViewById(R.id.btn_4)
        btn_4.setOnClickListener {
            setTextFieldsNum("4")
        }

        btn_5 = findViewById(R.id.btn_5)
        btn_5.setOnClickListener {
            setTextFieldsNum("5")
        }

        btn_6 = findViewById(R.id.btn_6)
        btn_6.setOnClickListener {
            setTextFieldsNum("6")
        }

        btn_7 = findViewById(R.id.btn_7)
        btn_7.setOnClickListener {
            setTextFieldsNum("7")
        }

        btn_8 = findViewById(R.id.btn_8)
        btn_8.setOnClickListener {
            setTextFieldsNum("8")
        }

        btn_9 = findViewById(R.id.btn_9)
        btn_9.setOnClickListener {
            setTextFieldsNum("9")
        }

        minus_btn = findViewById(R.id.minus_btn)
        minus_btn.setOnClickListener {
            minus()
        }

        plus_btn = findViewById(R.id.plus_btn)
        plus_btn.setOnClickListener {
            plus()
        }

        mult_btn = findViewById(R.id.mult_btn)
        mult_btn.setOnClickListener {
            multiply()
        }

        division_btn = findViewById(R.id.division_btn)
        division_btn.setOnClickListener {
            divide()
        }

        dot_btn = findViewById(R.id.dot_btn)
        dot_btn.setOnClickListener {
            setTextFieldsNum(".")
        }

        clear_btn = findViewById(R.id.clear_btn)
        result_text = findViewById(R.id.result_text)
        clear_btn.setOnClickListener {
            math_operation.text = ""
            result_text.text = ""
            symbol_text.text = ""
            previousOperation = ""
        }

        back_btn = findViewById(R.id.back_btn)
        math_operation = findViewById(R.id.math_operation)
        back_btn.setOnClickListener {
            val str = math_operation.text.toString()
            if (str.isNotEmpty()) {
                math_operation.text = str.substring(0, str.length - 1)
            }
        }

        symbol_text = findViewById(R.id.symbol_text)
        previousOperation = ""

        equal_btn = findViewById(R.id.equal_btn)
        equal_btn.setOnClickListener {
            calculateResult()
        }
    }

    fun setTextFieldsNum(str: String) {
        math_operation = findViewById(R.id.math_operation)
        math_operation.append(str)
    }

    fun setTextFieldsSym(str: String) {
        symbol_text = findViewById(R.id.symbol_text)
        symbol_text.text = str
    }

    fun plus() {
        plus_btn.setOnClickListener {
            setTextFieldsSym("+")
            if (result_text.text.isNotEmpty()) {
                val result = performOperation(previousOperation, result_text.text.toString(), math_operation.text.toString())
                result_text.text = result.toString()
                math_operation.text = ""
            } else {
                result_text.text = math_operation.text
                math_operation.text = ""
            }
            previousOperation = "+"
        }
    }

    fun minus() {
        minus_btn.setOnClickListener {
            setTextFieldsSym("-")
            if (result_text.text.isNotEmpty()) {
                val result = performOperation(previousOperation, result_text.text.toString(), math_operation.text.toString())
                result_text.text = result.toString()
                math_operation.text = ""
            } else {
                result_text.text = math_operation.text
                math_operation.text = ""
            }
            previousOperation = "-"
        }
    }

    fun multiply() {
        mult_btn.setOnClickListener {
            setTextFieldsSym("*")
            if (result_text.text.isNotEmpty()) {
                val result= performOperation(previousOperation, result_text.text.toString(), math_operation.text.toString())
                result_text.text = result.toString()
                math_operation.text = ""
            } else {
                result_text.text = math_operation.text
                math_operation.text = ""
            }
            previousOperation = "*"
        }
    }

    fun divide() {
        division_btn.setOnClickListener {
            setTextFieldsSym("/")
            if (result_text.text.isNotEmpty()) {
                val result = performOperation(previousOperation, result_text.text.toString(), math_operation.text.toString())
                result_text.text = result.toString()
                math_operation.text = ""
            } else {
                result_text.text = math_operation.text
                math_operation.text = ""
            }
            previousOperation = "/"
        }
    }


    private fun performOperation(operation: String, num1: String, num2: String): Double {
        val operand1 = num1.toDouble()
        val operand2 = num2.toDouble()

        return when (operation) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> {
                if (operand2 != 0.0) {
                    operand1 / operand2
                } else {
                    throw ArithmeticException("Division by zero")
                }
            }
            else -> throw IllegalArgumentException("Unsupported operation: $operation")
        }
    }

    private fun calculateResult() {
        val operation = symbol_text.text.toString()
        val num1 = result_text.text.toString()
        val num2 = math_operation.text.toString()

        if (operation.isNotEmpty() && num1.isNotEmpty() && num2.isNotEmpty()) {
            try {
                val result = performOperation(operation, num1, num2)
                math_operation.text = result.toString()
                result_text.text = ""
                symbol_text.text = ""
                previousOperation = ""
            } catch (e: ArithmeticException) {
                math_operation.text = "Деление на 0!"
            }
        }
    }
}