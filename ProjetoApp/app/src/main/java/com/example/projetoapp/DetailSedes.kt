package com.example.projetoapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DetailSedes : AppCompatActivity() {

    var idVenues : String? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailsedes)


        val bundle = intent.extras
        bundle?.let{
            idVenues = it.getString("idVenues")
        }

        val intentResult = Intent()
        val sname = findViewById<EditText>(R.id.editTextNameS)
        val morada = findViewById<EditText>(R.id.editTextMoradaS)
        val addS =findViewById<Button>(R.id.btnAddS)

        addS.setOnClickListener {
            if (sname.text.toString() == "" || morada.text.toString() == "" ) {

                Toast.makeText(applicationContext, getString(R.string.camposvazios), Toast.LENGTH_SHORT).show()

            }
            else {
                VolleyHelper.instance.addSedes(
                    this@DetailSedes,
                    idVenues?.toInt()?.plus(1).toString(),
                    sname.text.toString(),
                    morada.text.toString()
                ) {
                    if (it) {
                        val intent = Intent(this@DetailSedes, Sedes::class.java)
                        Toast.makeText(
                            this@DetailSedes,
                            this@DetailSedes.getString(R.string.add_sedessuccess),
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@DetailSedes,
                            this@DetailSedes.getString(R.string.add_sedesfailed),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                setResult(Activity.RESULT_OK, intentResult)
                finish()
            }
        }
    }
}