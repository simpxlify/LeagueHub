package com.example.projetoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoapp.models.ModeloJogadores
import kotlinx.android.synthetic.main.activity_jogadores.*
import org.json.JSONObject

class Jogadores : AppCompatActivity() {

    var idTeam : String? = null
    var listaJogadores : MutableList<ModeloJogadores> = ArrayList()
    var jogadoresAdapter : JogadoresAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogadores)

        val homeBtn = findViewById<ImageButton>(R.id.homeBtn)
        val torneiosBtn = findViewById<ImageButton>(R.id.torneiosBtn)
        val equipasBtn = findViewById<ImageButton>(R.id.equipasBtn)
        val jogadoresBtn = findViewById<ImageButton>(R.id.jogadoresBtn)
        val sedesBtn = findViewById<ImageButton>(R.id.sedesBtn)
        val btnCriarJog = findViewById<Button>(R.id.btnCriarJog)


        jogadoresAdapter = JogadoresAdapter()
        listViewJogadores.adapter = jogadoresAdapter

        val bundle = intent.extras
        bundle?.let{
            idTeam = it.getString("idTeam")
        }


        VolleyHelper.instance.getListarJogadores(this ){response ->
            response?.let {
                for(index in 0 until it.length()){
                    val jsonPlayer = it[index] as JSONObject
                    listaJogadores.add(ModeloJogadores.parseJson(jsonPlayer))

                }
                jogadoresAdapter?.notifyDataSetChanged()
            }
        }

        btnCriarJog.setOnClickListener {
           val intent = Intent(this@Jogadores, DetailJogador::class.java)
            intent.putExtra("idPlayer", listaJogadores.size.toString())
            intent.putExtra("idTeam", idTeam)
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

inner class JogadoresAdapter : BaseAdapter(){
 override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
     val rowView = layoutInflater.inflate(R.layout.row_jogadores,parent,false)
     val textApresentarFNP = rowView.findViewById<TextView>(R.id.TextFirstNameP)
     val textApresentarLNP = rowView.findViewById<TextView>(R.id.TextLastNameP)
     val textApresentarAP = rowView.findViewById<TextView>(R.id.TextAgeP)
     val textApresentarHP = rowView.findViewById<TextView>(R.id.TextHeightP)


     textApresentarFNP.text = listaJogadores[position].first_name
     textApresentarLNP.text = listaJogadores[position].last_name
     textApresentarAP.text = listaJogadores[position].age
     textApresentarHP.text = listaJogadores[position].height

     rowView.setOnClickListener {
         val intent = Intent(this@Jogadores, DetailRowJogadores::class.java)
         intent.putExtra("idPlayer", listaJogadores[position].idPlayer.toString())
         startActivity(intent)
     }
     val imgbuttonDelete = rowView.findViewById<ImageView>(R.id.imageViewDeleteJ)
     imgbuttonDelete.setOnClickListener{
         val intent = Intent(this@Jogadores, Jogadores::class.java)
         VolleyHelper.instance.deleteIdJogadores(this@Jogadores, listaJogadores[position].idPlayer.toString()){
             if(it){
                 Toast.makeText(applicationContext, this@Jogadores.getString(R.string.add_delsuccess), Toast.LENGTH_LONG).show()
             } else{
                 Toast.makeText(applicationContext, this@Jogadores.getString(R.string.add_delfailed), Toast.LENGTH_LONG).show()
             }
         }
         startActivity(intent)
     }
     return rowView
 }
 override fun getItem(position: Int): Any {
     return listaJogadores[position]
 }
 override fun getItemId(position: Int): Long {
     return 0
 }
 override fun getCount(): Int {
     return listaJogadores.size
 }


}

}