package com.example.projetoapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DetailJogos : AppCompatActivity() {

    var idTournaments : String? =  null
    var idGame : Int? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailjogos)

        val bundle = intent.extras
        bundle?.let{
            idGame = it.getInt("idGame")
            idTournaments = it.getString("idTournaments")
        }

        val intentResult = Intent()
        val id1 = findViewById<EditText>(R.id.editTextIdUm)
        val id2 = findViewById<EditText>(R.id.editTextIdDois)
        val n1 = findViewById<EditText>(R.id.editTextNomeEquipaUm)
        val n2 = findViewById<EditText>(R.id.editTextNomeEquipaDois)
        val g1 = findViewById<EditText>(R.id.editTextGolosUm)
        val g2 =findViewById<EditText>(R.id.editTextGolosDois)
        val addJogo =findViewById<Button>(R.id.btnAddJogo)

        addJogo.setOnClickListener {
            if (id1.text.toString() == "" || id2.text.toString() == "" || n1.text.toString() == "" || n2.text.toString() == "" || g1.text.toString() == "" || g2.text.toString() == "") {

                Toast.makeText(applicationContext, getString(R.string.camposvazios), Toast.LENGTH_SHORT).show()

            }
            else {
                VolleyHelper.instance.addJogo(
                    this@DetailJogos,
                    idGame?.plus(1).toString(),
                    id1.text.toString(),
                    id2.text.toString(),
                    n1.text.toString(),
                    n2.text.toString(),
                    g1.text.toString(),
                    g2.text.toString(),
                    idTournaments.toString()
                ) {
                    if (it) {
                        val intent = Intent(this@DetailJogos, Jogos::class.java)
                        intent.putExtra("idTournaments", idTournaments)
                        Toast.makeText(
                            this@DetailJogos,
                            this@DetailJogos.getString(R.string.add_jogossuccess),
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@DetailJogos,
                            this@DetailJogos.getString(R.string.add_jogosfailed),
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