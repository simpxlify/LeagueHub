package com.example.projetoapp

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val textViewShadow = findViewById<View>(R.id.textViewLogin) as TextView
        textViewShadow.paint.strokeWidth = 5f
        textViewShadow.paint.style = Paint.Style.STROKE

        val btnEntrarLogin = findViewById<Button>(R.id.btnEntrarLogin)
        val txtRegistese = findViewById<TextView>(R.id.txtRegistese)
        val txtPW = findViewById<TextView>(R.id.txtPW)
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)

        btnEntrarLogin.setOnClickListener {
            if (username.text.toString() == "" || password.text.toString() == "" ) {

                Toast.makeText(applicationContext, getString(R.string.camposvazios), Toast.LENGTH_SHORT).show()

            }
            else {
                VolleyHelper.instance.userLogin(
                    this@Login,
                    username.text.toString(),
                    password.text.toString()
                ) {
                    if (it) {
                        val intent = Intent(this@Login, Home::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@Login,
                            this@Login.getString(R.string.login_failed),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        txtRegistese.setOnClickListener {
            val intent = Intent(this, Registo::class.java)
            startActivity(intent)
        }
        txtPW.setOnClickListener {
            val intent = Intent(this, Recuperarpw::class.java)
            startActivity(intent)
        }
    }
    }

