package com.example.projetoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.projetoapp.models.ModeloTorneios
import org.json.JSONObject

class DetailRowTorneio : AppCompatActivity() {

        var idTournaments : String? = null
        var rowlistaTorneios : MutableList<ModeloTorneios> = ArrayList()


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_detail_row_torneio)


            val homeBtn = findViewById<ImageButton>(R.id.homeBtn)
            val torneiosBtn = findViewById<ImageButton>(R.id.torneiosBtn)
            val equipasBtn = findViewById<ImageButton>(R.id.equipasBtn)
            val jogadoresBtn = findViewById<ImageButton>(R.id.jogadoresBtn)
            val sedesBtn = findViewById<ImageButton>(R.id.sedesBtn)
            val btnListarJogos = findViewById<Button>(R.id.btnListarJogos)



            val bundle = intent.extras
            bundle?.let{
                idTournaments = it.getString("idTournaments")
            }
            btnListarJogos.setOnClickListener {
                val intent = Intent(this, Jogos::class.java)
                intent.putExtra("idTournaments", idTournaments.toString())
                startActivity(intent)
            }

            val btnVerEquipasNoTorneio = findViewById<Button>(R.id.btnVerEquipasNoTorneio)
            btnVerEquipasNoTorneio.setOnClickListener {
                val intent = Intent(this@DetailRowTorneio, Equipas::class.java)
                intent.putExtra("idTournaments", idTournaments.toString())
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

            val textApresentarNR = findViewById<TextView>(R.id.textViewNomeRow)
            val textApresentarPR = findViewById<TextView>(R.id.textViewPremioRow)
            val textApresentarDIR = findViewById<TextView>(R.id.textViewDataIRow)
            val textApresentarDFR = findViewById<TextView>(R.id.textViewDataFRow)

            VolleyHelper.instance.getListarIdTorneio(this@DetailRowTorneio, idTournaments.toString()) { response ->
                response?.let {
                    for(index in 0 until it.length()){
                        val jsonTournaments = it[index] as JSONObject
                        rowlistaTorneios.add(ModeloTorneios.parseJson(jsonTournaments))

                        textApresentarNR.text = rowlistaTorneios[index].name
                        textApresentarPR.text = rowlistaTorneios[index].prize
                        textApresentarDIR.text = rowlistaTorneios[index].initial_date
                        textApresentarDFR.text = rowlistaTorneios[index].last_date


                    }

                }

            }



        }


}