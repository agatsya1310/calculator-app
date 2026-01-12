package com.example.mycalc

import android.media.VolumeShaper
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var previousCalculationTextView: TextView
    private var firstNumber=0.0
    private var operation=""
    private var isNewOperation=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        resultTextView=findViewById(R.id.resultTextView2)
        previousCalculationTextView=findViewById(R.id.previousCalculationTextView)

        val btn0: Button=findViewById(R.id.btn0)
        val btn1: Button=findViewById(R.id.btn1)
        val btn2: Button=findViewById(R.id.btn2)
        val btn3: Button=findViewById(R.id.btn3)
        val btn4: Button=findViewById(R.id.btn4)
        val btn5: Button=findViewById(R.id.btn5)
        val btn6: Button=findViewById(R.id.btn6)
        val btn7: Button=findViewById(R.id.btn7)
        val btn8: Button=findViewById(R.id.btn8)
        val btn9: Button=findViewById(R.id.btn9)

        val add: Button=findViewById(R.id.btnAddition)
        val sub: Button=findViewById(R.id.btnSubtraction)
        val mul: Button=findViewById(R.id.btnMultiplication)
        val div: Button=findViewById(R.id.btnDivision)

        val dot: Button=findViewById(R.id.btnDot)
        val backspace: Button=findViewById(R.id.btnBackspace)
        val clear: Button=findViewById(R.id.btnClear)
        val percentage: Button=findViewById(R.id.btnPercentage)
        val equal: Button=findViewById(R.id.btnEqual)

        btn0.setOnClickListener { appendNumber("0") }
        btn1.setOnClickListener { appendNumber("1") }
        btn2.setOnClickListener { appendNumber("2") }
        btn3.setOnClickListener { appendNumber("3") }
        btn4.setOnClickListener { appendNumber("4") }
        btn5.setOnClickListener { appendNumber("5") }
        btn6.setOnClickListener { appendNumber("6") }
        btn7.setOnClickListener { appendNumber("7") }
        btn8.setOnClickListener { appendNumber("8") }
        btn9.setOnClickListener { appendNumber("9") }
        dot.setOnClickListener { appendNumber(".") }

        add.setOnClickListener { setOperation("+") }
        mul.setOnClickListener { setOperation("*") }
        div.setOnClickListener { setOperation("÷") }
        sub.setOnClickListener { setOperation("-") }
        percentage.setOnClickListener { setOperation("%") }

        equal.setOnClickListener { calculateResult() }
        clear.setOnClickListener { clearCalculator() }
        backspace.setOnClickListener { deleteNumber() }



    }
    private fun appendNumber(number: String){
        if (isNewOperation){
            resultTextView.text=number
            isNewOperation=false
        }
        else{
            resultTextView.text="${resultTextView.text}$number"
        }
    }

    private fun setOperation(op: String){
        firstNumber=resultTextView.text.toString().toDouble()
        operation=op
        isNewOperation=true
        previousCalculationTextView.text="$firstNumber $operation"
        resultTextView.text="0.0"
    }

    private fun calculateResult(){
        try{
            val secondNumber = resultTextView.text.toString().toDouble()
            val result = when(operation){
                "+"-> firstNumber + secondNumber
                "-"-> firstNumber - secondNumber
                "*"-> firstNumber * secondNumber
                "÷"-> firstNumber / secondNumber
                "%"-> (firstNumber * secondNumber) / 100
                else -> secondNumber
            }

            previousCalculationTextView.text="$firstNumber $operation $secondNumber ="
            resultTextView.text= result.toString()
            isNewOperation=true

        }catch (e: Exception){
            resultTextView.text="Error"
        }
    }

    private fun clearCalculator(){
        resultTextView.text="0"
        previousCalculationTextView.text=""
        firstNumber=0.0
        operation=""
        isNewOperation=true
    }

    private fun deleteNumber() {
        if (resultTextView.text.isNotEmpty() && resultTextView.text != "0.0" && resultTextView.text != "Error") {
            resultTextView.text = resultTextView.text.dropLast(1)
        }
        else{
            Toast.makeText(this,"Invalid Operation",Toast.LENGTH_SHORT).show()
        }
    }
}
