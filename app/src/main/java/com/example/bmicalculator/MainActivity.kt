package com.example.bmicalculator

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.R.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_main)

        val weightText = findViewById<EditText>(id.etWeight)
        val heightText = findViewById<EditText>(id.etHeight)
        val calcBtn = findViewById<Button>(id.btnCalculate)

        calcBtn.setOnClickListener() {

            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            if (validateInput(weight,height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * height.toFloat() / 100)
                //get results with two decimal places
                val bmi2Digits = String.format("%2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }

        }

    }
    private fun validateInput(weight:String?, height:String?):Boolean {

        return when{

              weight.isNullOrEmpty() ->{
                  Toast.makeText(this, "Weight Is Empty",Toast.LENGTH_LONG).show()
                  return false
              }

            height.isNullOrEmpty() ->{
                Toast.makeText(this, "height Is Empty",Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayResult(bmi: Float) {

        val resultIndex = findViewById<TextView>(id.tvIndex)
        val resultDescription = findViewById<TextView>(id.tvResult)
        val info = findViewById<TextView>(id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = "Normal Range Is 18.5 - 24.9"

        var result = ""
        var color = 0

        when {
            bmi < 18.50 -> {

                result = "Under Weight"
                color = R.color.Under_Weight
            }

            bmi in 18.50..24.99 -> {
                result = "You are Health"
                color = R.color.Normal
            }

            bmi in 25.00..29.99 -> {

                result = "Over Weight"
                color = R.color.Over_Weight

            }

            bmi > 29.99 -> {

                result = "Obesity"
                color = R.color.Obese

            }


        }

        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text = result



    }
}




