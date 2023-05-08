package com.sebastiaovinicius.taxasequivalentes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun convertAnualToMensal( aoAno: Double): Double {

        // mensal= (1+anual)^¹/¹² -1

        var mensal= Math.pow(  (1+aoAno), 1/12.toDouble()   ) - 1

        return mensal

    }
}