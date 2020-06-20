package com.example.projetoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.example.projetoapp.models.ModeloEquipas
import com.example.projetoapp.models.ModeloJogadores
import com.example.projetoapp.models.ModeloTorneios
import org.json.JSONObject

class DetailRowJogadores : AppCompatActivity() {

    var idPlayer : String? = null
    var rowlistaJogadores : MutableList<ModeloJogadores> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_row_jogadores)


        val homeBtn = findViewById<ImageButton>(R.id.homeBtn)
        val torneiosBtn = findViewById<ImageButton>(R.id.torneiosBtn)
        val equipasBtn = findViewById<ImageButton>(R.id.equipasBtn)
        val jogadoresBtn = findViewById<ImageButton>(R.id.jogadoresBtn)
        val sedesBtn = findViewById<ImageButton>(R.id.sedesBtn)


        val bundle = intent.extras
        bundle?.let{
            idPlayer = it.getString("idPlayer")
        }

        homeBtn.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        torneiosBtn.setOnClickListener {
            val intent = Intent(this, Torneios::class.java)
            startActivity(intent)

        }

        equipasBtn.setOnClickListener {
            val intent = Intent(this, Equipas::class.java)
            startActivity(intent)
        }

        jogadoresBtn.setOnClickListener {
            val intent = Intent(this, Jogadores::class.java)
            startActivity(intent)
        }

        sedesBtn.setOnClickListener {
            val intent = Intent(this, Sedes::class.java)
            startActivity(intent)
        }

        val textApresentarFNR = findViewById<TextView>(R.id.textViewFNameRow)
        val textApresentarLNR = findViewById<TextView>(R.id.textViewLNameRow)
        val textApresentarAR = findViewById<TextView>(R.id.textViewAgeRow)
        val textApresentarHR = findViewById<TextView>(R.id.textViewHeightRow)
        val textApresentarCR = findViewById<TextView>(R.id.textViewCountryRow)
        val textApresentarPR = findViewById<TextView>(R.id.textViewPositionRow)

        VolleyHelper.instance.getListarIdJogadores(this@DetailRowJogadores, idPlayer.toString()) { response ->
            response?.let {
                for(index in 0 until it.length()){
                    val jsonTournaments = it[index] as JSONObject
                    rowlistaJogadores.add(ModeloJogadores.parseJson(jsonTournaments))

                    textApresentarFNR.text = rowlistaJogadores[index].first_name
                    textApresentarLNR.text = rowlistaJogadores[index].last_name
                    textApresentarAR.text = rowlistaJogadores[index].age
                    textApresentarHR.text = rowlistaJogadores[index].height
                    textApresentarCR.text = rowlistaJogadores[index].country
                    textApresentarPR.text = rowlistaJogadores[index].position


                }

            }

        }
    }
}