package com.example.projetoapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoapp.models.ModeloSedes
import kotlinx.android.synthetic.main.activity_sedes.*
import org.json.JSONObject

class Sedes : AppCompatActivity() {

    var listaSedes : MutableList<ModeloSedes> = ArrayList()
    var sedesAdapter : SedesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sedes)


        val homeBtn = findViewById<ImageButton>(R.id.homeBtn)
        val torneiosBtn = findViewById<ImageButton>(R.id.torneiosBtn)
        val equipasBtn = findViewById<ImageButton>(R.id.equipasBtn)
        val jogadoresBtn = findViewById<ImageButton>(R.id.jogadoresBtn)
        val sedesBtn = findViewById<ImageButton>(R.id.sedesBtn)
        val btnCriarSedes = findViewById<Button>(R.id.btnCriarSedes)


        sedesAdapter = SedesAdapter()
        listViewSedes.adapter = sedesAdapter

        VolleyHelper.instance.getListarSedes(this ){response ->
            response?.let {
                for(index in 0 until it.length()){
                    val jsonPlayer = it[index] as JSONObject
                    listaSedes.add(ModeloSedes.parseJson(jsonPlayer))
                }
                sedesAdapter?.notifyDataSetChanged()
            }
        }

        btnCriarSedes.setOnClickListener {
            val intent = Intent(this@Sedes, DetailSedes::class.java)
            intent.putExtra("idVenues", listaSedes.size.toString())
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

    inner class SedesAdapter : BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_sedes,parent,false)
            val textApresentarES = rowView.findViewById<TextView>(R.id.TextEstadioS)
            val textApresentarMS = rowView.findViewById<TextView>(R.id.TextMoradaS)



            textApresentarES.text = listaSedes[position].name_venue
            textApresentarMS.text = listaSedes[position].location_venues

            val imgbuttonDelete = rowView.findViewById<ImageView>(R.id.imageViewDeleteS)
            imgbuttonDelete.setOnClickListener{
                val intent = Intent(this@Sedes, Sedes::class.java)
                VolleyHelper.instance.deleteIdSedes(this@Sedes, listaSedes[position].idVenues.toString()){
                    if(it){
                        Toast.makeText(applicationContext, this@Sedes.getString(R.string.add_delsuccess), Toast.LENGTH_LONG).show()
                    } else{
                        Toast.makeText(applicationContext, this@Sedes.getString(R.string.add_delfailed), Toast.LENGTH_LONG).show()
                    }
                }
                startActivity(intent)
            }
            val imageViewMaps = rowView.findViewById<ImageView>(R.id.imageViewMaps)

            imageViewMaps.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://www.google.pt/maps/place/Est%C3%A1cao+Est%C3%A1dio+do+Drag%C3%A3o/@41.1606878,-8.5845264,17z/data=!3m1!4b1!4m5!3m4!1s0xd246487faab04a3:0x114b496a3f031663!8m2!3d41.1606838!4d-8.5823377")
                startActivity(intent)
            }

            return rowView
        }
        override fun getItem(position: Int): Any {
            return listaSedes[position]
        }
        override fun getItemId(position: Int): Long {
            return 0
        }
        override fun getCount(): Int {
            return listaSedes.size
        }


    }

}