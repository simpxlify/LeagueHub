package com.example.projetoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.projetoapp.models.ModeloEquipas
import com.example.projetoapp.models.ModeloTorneios
import kotlinx.android.synthetic.main.activity_detail_row_equipa.*
import org.json.JSONObject


class DetailRowEquipa : AppCompatActivity() {

    var idTeam : String? = null
    var rowlistaEquipas : MutableList<ModeloEquipas> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_row_equipa)


        val homeBtn = findViewById<ImageButton>(R.id.homeBtn)
        val torneiosBtn = findViewById<ImageButton>(R.id.torneiosBtn)
        val equipasBtn = findViewById<ImageButton>(R.id.equipasBtn)
        val jogadoresBtn = findViewById<ImageButton>(R.id.jogadoresBtn)
        val sedesBtn = findViewById<ImageButton>(R.id.sedesBtn)
        val btnListarLineup = findViewById<Button>(R.id.btnListarLineup)


        val bundle = intent.extras
        bundle?.let{
            idTeam = it.getString("idTeam")
        }


        btnListarLineup.setOnClickListener {
            val intent = Intent(this, Lineup::class.java)
            intent.putExtra("idTeam", idTeam.toString())
            startActivity(intent)
        }
        val btnVerJogadoresdaEquipa = findViewById<Button>(R.id.btnVerJogadoresdaEquipa)

        btnVerJogadoresdaEquipa.setOnClickListener {
            val intent = Intent(this@DetailRowEquipa, Jogadores::class.java)
            intent.putExtra("idTeam", idTeam.toString())
            startActivity(intent)

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

        val textApresentarNRE = findViewById<TextView>(R.id.textViewNomeRowE)
        val textApresentarIR = findViewById<TextView>(R.id.textViewInitialsRow)
        val textApresentarLR = findViewById<TextView>(R.id.textViewLocationRow)
        val textApresentarCR = findViewById<TextView>(R.id.textViewCoachRow)


        VolleyHelper.instance.getListarIdEquipa(this@DetailRowEquipa, idTeam.toString()) { response ->
            response?.let {
                for(index in 0 until it.length()){
                    val jsonTournaments = it[index] as JSONObject
                    rowlistaEquipas.add(ModeloEquipas.parseJson(jsonTournaments))

                    textApresentarNRE.text = rowlistaEquipas[index].name
                    textApresentarIR.text = rowlistaEquipas[index].initials
                    textApresentarLR.text = rowlistaEquipas[index].location
                    textApresentarCR.text = rowlistaEquipas[index].coach



                }

            }

        }

    }
}