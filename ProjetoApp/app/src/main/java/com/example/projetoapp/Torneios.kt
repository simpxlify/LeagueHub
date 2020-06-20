package com.example.projetoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.projetoapp.models.ModeloTorneios
import kotlinx.android.synthetic.main.activity_torneios.*
import org.json.JSONObject

class Torneios : AppCompatActivity() {

    var listaTorneios : MutableList<ModeloTorneios> = ArrayList()
    var torneiosAdapter : TorneiosAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_torneios)


        val homeBtn = findViewById<ImageButton>(R.id.homeBtn)
        val torneiosBtn = findViewById<ImageButton>(R.id.torneiosBtn)
        val equipasBtn = findViewById<ImageButton>(R.id.equipasBtn)
        val jogadoresBtn = findViewById<ImageButton>(R.id.jogadoresBtn)
        val sedesBtn = findViewById<ImageButton>(R.id.sedesBtn)
        val btnCriarTorneio = findViewById<Button>(R.id.btnCriarTorneio)


        torneiosAdapter = TorneiosAdapter()
        listViewTorneios.adapter = torneiosAdapter

        VolleyHelper.instance.getListarTorneios(this ){response ->
            response?.let {
                for(index in 0 until it.length()){
                    val jsonPlayer = it[index] as JSONObject
                    listaTorneios.add(ModeloTorneios.parseJson(jsonPlayer))
                }
                torneiosAdapter?.notifyDataSetChanged()
            }
        }

        btnCriarTorneio.setOnClickListener {
            val intent = Intent(this@Torneios, DetailTorneio::class.java)
            intent.putExtra("idTournaments", listaTorneios.size.toString())
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
    }

    inner class TorneiosAdapter : BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_torneios,parent,false)
            val textApresentarNT = rowView.findViewById<TextView>(R.id.TextNameT)
            val textApresentarPT = rowView.findViewById<TextView>(R.id.TextPremioT)



            textApresentarNT.text = listaTorneios[position].name
            textApresentarPT.text = listaTorneios[position].prize

            rowView.setOnClickListener {
                val intent = Intent(this@Torneios, DetailRowTorneio::class.java)
                intent.putExtra("idTournaments", listaTorneios[position].idTournaments.toString())
                startActivity(intent)
            }

            val imgbuttonDelete = rowView.findViewById<ImageView>(R.id.imageViewDeleteT)
            imgbuttonDelete.setOnClickListener{
                val intent = Intent(this@Torneios, Torneios::class.java)
                VolleyHelper.instance.deleteIdTorneios(this@Torneios, listaTorneios[position].idTournaments.toString()){
                    if(it){
                        Toast.makeText(applicationContext, this@Torneios.getString(R.string.add_delsuccess), Toast.LENGTH_LONG).show()
                    } else{
                        Toast.makeText(applicationContext, this@Torneios.getString(R.string.add_delfailed), Toast.LENGTH_LONG).show()
                    }
                }
                startActivity(intent)
            }

            return rowView
        }
        override fun getItem(position: Int): Any {
            return listaTorneios[position]
        }
        override fun getItemId(position: Int): Long {
            return 0
        }
        override fun getCount(): Int {
            return listaTorneios.size
        }


    }
}