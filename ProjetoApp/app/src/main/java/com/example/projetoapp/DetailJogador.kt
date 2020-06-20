package com.example.projetoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailJogador : AppCompatActivity() {

    var idTeam : String? =  ""
    var idPlayer : String? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailjogador)

        val bundle = intent.extras
        bundle?.let{
            idTeam = it.getString("idTeam")
            idPlayer = it.getString("idPlayer")
        }

        val intentResult = Intent()
        val fname = findViewById<EditText>(R.id.editTextFirstNameP)
        val lname = findViewById<EditText>(R.id.editTextLastNameP)
        val age = findViewById<EditText>(R.id.editTextAgeP)
        val height =findViewById<EditText>(R.id.editTextHeightP)
        val country = findViewById<EditText>(R.id.editTextCountryP)
        val position =findViewById<EditText>(R.id.editTextPositionP)


        val addP =findViewById<Button>(R.id.btnAddP)

        addP.setOnClickListener {
            idTeam = "0"
            if (fname.text.toString() == "" || lname.text.toString() == "" || age.text.toString() == "" || height.text.toString() == "" || country.text.toString() == "" || position.text.toString() == "") {

                Toast.makeText(applicationContext, getString(R.string.camposvazios), Toast.LENGTH_SHORT).show()

            }
            else {
                VolleyHelper.instance.addJogadores(
                    this@DetailJogador,
                    idPlayer?.toInt()?.plus(1).toString(),
                    fname.text.toString(),
                    lname.text.toString(),
                    age.text.toString(),
                    height.text.toString(),
                    country.text.toString(),
                    position.text.toString(),
                    idTeam.toString()
                ) {
                    if (it) {
                        val intent = Intent(this@DetailJogador, Jogadores::class.java)
                        Toast.makeText(
                            this@DetailJogador,
                            this@DetailJogador.getString(R.string.add_jogadorsuccess),
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@DetailJogador,
                            this@DetailJogador.getString(R.string.add_jogadorfailed),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                setResult(Activity.RESULT_OK, intentResult)
                finish()
            }
        }

        val btnAddPnaEquipa =findViewById<Button>(R.id.btnAddPnaEquipa)
        btnAddPnaEquipa.setOnClickListener {
            if (fname.text.toString() == "" || lname.text.toString() == "" || age.text.toString() == "" || height.text.toString() == "" || country.text.toString() == "" || position.text.toString() == "") {

                Toast.makeText(applicationContext, getString(R.string.camposvazios), Toast.LENGTH_SHORT).show()

            }
            else {
                VolleyHelper.instance.addJogadoresNaEquipa(
                    this@DetailJogador,
                    idPlayer?.toInt()?.plus(1).toString(),
                    fname.text.toString(),
                    lname.text.toString(),
                    age.text.toString(),
                    height.text.toString(),
                    country.text.toString(),
                    position.text.toString(),
                    idTeam.toString()
                ) {
                    if (it) {
                        val intent = Intent(this@DetailJogador, Jogadores::class.java)
                        Toast.makeText(
                            this@DetailJogador,
                            this@DetailJogador.getString(R.string.add_jogadorsuccess),
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@DetailJogador,
                            this@DetailJogador.getString(R.string.add_jogadorfailed),
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