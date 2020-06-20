package com.example.projetoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoapp.models.ModeloEquipas
import kotlinx.android.synthetic.main.activity_detail_row_equipa.*
import kotlinx.android.synthetic.main.activity_equipas.*
import org.json.JSONObject

class Equipas : AppCompatActivity() {

    var idTournaments : String? = null
    var listaEquipas : MutableList<ModeloEquipas> = ArrayList()
    var equipasAdapter : EquipasAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipas)

        val homeBtn = findViewById<ImageButton>(R.id.homeBtn)
        val torneiosBtn = findViewById<ImageButton>(R.id.torneiosBtn)
        val equipasBtn = findViewById<ImageButton>(R.id.equipasBtn)
        val jogadoresBtn = findViewById<ImageButton>(R.id.jogadoresBtn)
        val sedesBtn = findViewById<ImageButton>(R.id.sedesBtn)
        val btnCriarEquipa = findViewById<Button>(R.id.btnCriarEquipa)


        equipasAdapter = EquipasAdapter()
        listViewEquipas.adapter = equipasAdapter

        val bundle = intent.extras
        bundle?.let{
            idTournaments = it.getString("idTournaments")
        }

        VolleyHelper.instance.getListarEquipas(this@Equipas ){ response ->
            response?.let {
                for(index in 0 until it.length()){
                    val jsonPlayer = it[index] as JSONObject
                    listaEquipas.add(ModeloEquipas.parseJson(jsonPlayer))
                }
                equipasAdapter?.notifyDataSetChanged()
            }
        }

        btnCriarEquipa.setOnClickListener {
           val intent = Intent(this@Equipas, DetailEquipa::class.java)
            intent.putExtra("idTeam", listaEquipas.size.toString())
            intent.putExtra("idTournaments", idTournaments)
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

inner class EquipasAdapter : BaseAdapter(){
override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val rowView = layoutInflater.inflate(R.layout.row_equipas,parent,false)
    val textApresentarFNP = rowView.findViewById<TextView>(R.id.TextNameT)
    val textApresentarLNP = rowView.findViewById<TextView>(R.id.TextInitialsT)


    textApresentarFNP.text = listaEquipas[position].name
    textApresentarLNP.text = listaEquipas[position].initials


    rowView.setOnClickListener {
        val intent = Intent(this@Equipas, DetailRowEquipa::class.java)
        intent.putExtra("idTeam", listaEquipas[position].idTeam.toString())
        startActivity(intent)
    }

    val imgbuttonDelete = rowView.findViewById<ImageView>(R.id.imageViewDelete)
    imgbuttonDelete.setOnClickListener{
        val intent = Intent(this@Equipas, Equipas::class.java)
        VolleyHelper.instance.deleteIdEquipa(this@Equipas, listaEquipas[position].idTeam.toString()){
            if(it){
                Toast.makeText(applicationContext, this@Equipas.getString(R.string.add_delsuccess), Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(applicationContext, this@Equipas.getString(R.string.add_delfailed), Toast.LENGTH_LONG).show()
            }
        }
        startActivity(intent)
    }

    return rowView

    }
    override fun getItem(position: Int): Any {
    return listaEquipas[position]
    }
    override fun getItemId(position: Int): Long {
    return 0
    }
    override fun getCount(): Int {
    return listaEquipas.size
    }


}

}