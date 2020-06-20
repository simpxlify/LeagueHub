package com.example.projetoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailEquipa : AppCompatActivity() {

    var idTournaments : String? = ""
    var idTeam : String? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailequipa)

        val bundle = intent.extras
        bundle?.let {

            idTournaments = it.getString("idTournaments")
            idTeam = it.getString("idTeam")
        }

        val intentResult = Intent()
        val name = findViewById<EditText>(R.id.editTextNameT)
        val ini = findViewById<EditText>(R.id.editTextinitialsT)
        val location = findViewById<EditText>(R.id.editTextlocationT)
        val coach = findViewById<EditText>(R.id.editTextCoachT)
        val addE = findViewById<Button>(R.id.btnAddE)





        val btnAddEnoTorneio =findViewById<Button>(R.id.btnAddEnoTorneio)
        btnAddEnoTorneio.setOnClickListener {
            if (name.text.toString() == "" || ini.text.toString() == "" || location.text.toString() == "" || coach.text.toString() == "") {

                Toast.makeText(applicationContext, getString(R.string.camposvazios), Toast.LENGTH_SHORT).show()

            }
            else {
                VolleyHelper.instance.addEquipasNoTorneio(
                    this@DetailEquipa,
                    idTeam?.toInt()?.plus(1).toString(),
                    name.text.toString(),
                    ini.text.toString(),
                    location.text.toString(),
                    coach.text.toString(),
                    idTournaments.toString()
                ) {
                    if (it) {
                        val intent = Intent(this@DetailEquipa, Equipas::class.java)
                        Toast.makeText(
                            this@DetailEquipa,
                            this@DetailEquipa.getString(R.string.add_equipassuccess),
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@DetailEquipa,
                            this@DetailEquipa.getString(R.string.add_equipasfailed),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                setResult(Activity.RESULT_OK, intentResult)
                finish()
            }
        }

        addE.setOnClickListener {
            idTournaments = "0"
            if (name.text.toString() == "" || ini.text.toString() == "" || location.text.toString() == "" || coach.text.toString() == "") {

                Toast.makeText(applicationContext, getString(R.string.camposvazios), Toast.LENGTH_SHORT).show()

            }
            else {
                VolleyHelper.instance.addEquipas(
                    this@DetailEquipa,
                    idTeam?.toInt()?.plus(1).toString(),
                    name.text.toString(),
                    ini.text.toString(),
                    location.text.toString(),
                    coach.text.toString(),
                    idTournaments.toString()
                ) {
                    if (it) {
                        val intent = Intent(this@DetailEquipa, Equipas::class.java)
                        Toast.makeText(
                            this@DetailEquipa,
                            this@DetailEquipa.getString(R.string.add_equipassuccess),
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@DetailEquipa,
                            this@DetailEquipa.getString(R.string.add_equipasfailed),
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