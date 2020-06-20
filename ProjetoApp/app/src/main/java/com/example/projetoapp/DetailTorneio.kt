package com.example.projetoapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DetailTorneio : AppCompatActivity() {

    var idTournaments : String? =  null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailtorneio)

        val bundle = intent.extras
        bundle?.let{
            idTournaments = it.getString("idTournaments")
        }

        val intentResult = Intent()
        val tname = findViewById<EditText>(R.id.editTextNomeT)
        val premio = findViewById<EditText>(R.id.editTextPremioT)
        val datainicial = findViewById<EditText>(R.id.editTextInitialDateT)
        val datafinal =findViewById<EditText>(R.id.editTextLastDateT)
        val addT =findViewById<Button>(R.id.btnAddT)

        addT.setOnClickListener {
            if (tname.text.toString() == "" || premio.text.toString() == "" || datainicial.text.toString() == "" || datafinal.text.toString() == "" ) {

                Toast.makeText(applicationContext, getString(R.string.camposvazios), Toast.LENGTH_SHORT).show()

            }
            else {
                VolleyHelper.instance.addTorneios(
                    this@DetailTorneio,
                    idTournaments?.toInt()?.plus(1).toString(),
                    tname.text.toString(),
                    premio.text.toString(),
                    datainicial.text.toString(),
                    datafinal.text.toString()
                ) {
                    if (it) {
                        val intent = Intent(this@DetailTorneio, Torneios::class.java)
                        Toast.makeText(
                            this@DetailTorneio,
                            this@DetailTorneio.getString(R.string.add_torneiossuccess),
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@DetailTorneio,
                            this@DetailTorneio.getString(R.string.add_torneiosfailed),
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