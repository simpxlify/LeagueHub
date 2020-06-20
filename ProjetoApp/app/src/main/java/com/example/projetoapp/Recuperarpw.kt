package com.example.projetoapp

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_recuperarpw.*

class Recuperarpw : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperarpw)

        val textViewShadow = findViewById<View>(R.id.textViewRCPW) as TextView
        textViewShadow.paint.strokeWidth = 5f
        textViewShadow.paint.style = Paint.Style.STROKE

        val buttonRecover = findViewById<Button>(R.id.btnRecuppw)
        buttonRecover.setOnClickListener{
            if (password.text.toString() == confpassword.text.toString()) {
                if (username.toString() == "" || password.toString() == "" || confpassword.toString() == "") {
                    Toast.makeText(applicationContext, R.string.camposvazios, Toast.LENGTH_LONG).show()
                } else {
                    VolleyHelper.instance.recoverPassword(this@Recuperarpw, username.text.toString(), password.text.toString()) { response ->
                        if (response) {
                            val intent = Intent(this@Recuperarpw, Login::class.java)
                            Toast.makeText(applicationContext, R.string.add_recupsuccess, Toast.LENGTH_LONG).show()
                            startActivity(intent)
                        } else {
                            Toast.makeText(applicationContext, R.string.add_recupwfailed, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            } else {
                Toast.makeText(applicationContext, R.string.wpw, Toast.LENGTH_LONG).show()
            }
        }

    }
}
