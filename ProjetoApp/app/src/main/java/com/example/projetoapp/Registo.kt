package com.example.projetoapp

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class Registo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_registo)

        val textViewShadow = findViewById<View>(R.id.textViewREG) as TextView
        textViewShadow.paint.strokeWidth = 5f
        textViewShadow.paint.style = Paint.Style.STROKE

        val username = findViewById<EditText>(R.id.username)
        val name = findViewById<EditText>(R.id.name)
        val password = findViewById<EditText>(R.id.password)
        val email = findViewById<EditText>(R.id.email)
        val confpassword=findViewById<EditText>(R.id.confpassword)
        val register=findViewById<Button>(R.id.btnEntrarLogin)
        val checkBoxTermos=findViewById<CheckBox>(R.id.checkBoxTermos)

            register.setOnClickListener {
                if (password.text.toString()==confpassword.text.toString()) {

                    if (username.text.toString() == "" || name.text.toString() == "" || password.text.toString() == "" ||
                    email.text.toString() == "" || confpassword.text.toString() == "" || !checkBoxTermos.isChecked) {

                        Toast.makeText(applicationContext, getString(R.string.camposvazios), Toast.LENGTH_SHORT).show()

                    }
                    else {
                    VolleyHelper.instance.userRegister(this@Registo, username.text.toString(), name.text.toString(), email.text.toString(), password.text.toString()) {
                        if (it) {
                            val intent = Intent(this@Registo, Home::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@Registo, this@Registo.getString(R.string.registar_failed), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
                else {
                    Toast.makeText(this@Registo, this@Registo.getString(R.string.wpw), Toast.LENGTH_LONG).show()
                }
        }

        val txtTermos = findViewById<TextView>(R.id.txtTermos)

        txtTermos.setOnClickListener {
            val intent = Intent(this, Termos::class.java)
            startActivity(intent)
        }

    }


}