package com.example.projetoapp

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class Termos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_termos)

        val textViewShadow = findViewById<View>(R.id.textViewTermosc) as TextView
        textViewShadow.paint.strokeWidth = 5f
        textViewShadow.paint.style = Paint.Style.STROKE
    }
}
