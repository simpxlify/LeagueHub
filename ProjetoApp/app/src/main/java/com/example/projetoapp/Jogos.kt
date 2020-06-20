package com.example.projetoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.example.projetoapp.models.ModeloJogos
import kotlinx.android.synthetic.main.activity_jogos.*
import org.json.JSONObject


class Jogos : AppCompatActivity() {

    var idTournaments : String? =  null
    var tdsJogos: MutableList<ModeloJogos> = ArrayList()
    var listaJogos : MutableList<ModeloJogos> = ArrayList()
    var jogosAdapter : JogosAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogos)

        jogosAdapter = JogosAdapter()
        listViewJogos.adapter = jogosAdapter

        val bundle = intent.extras
        bundle?.let {

            idTournaments = it.getString("idTournaments")
        }


        VolleyHelper.instance.getListarIdJogos(this, idTournaments.toString() ) { response ->
            response?.let {
                for(index in 0 until it.length()){
                    val jsonJogos = it[index] as JSONObject
                    listaJogos.add(ModeloJogos.parseJson(jsonJogos))
                }
                jogosAdapter?.notifyDataSetChanged()
            }
        }

        VolleyHelper.instance.getListarJogos(this ) { response ->
            response?.let {
                for(index in 0 until it.length()){
                    val jsonJogos = it[index] as JSONObject
                    tdsJogos.add(ModeloJogos.parseJson(jsonJogos))
                }
                jogosAdapter?.notifyDataSetChanged()
            }
        }

        val btnCriarJogos = findViewById<Button>(R.id.btnCriarJogos)
        btnCriarJogos.setOnClickListener {
            val intent = Intent(this@Jogos, DetailJogos::class.java)
            intent.putExtra("idGame", tdsJogos.size)
            intent.putExtra("idTournaments", idTournaments.toString())
            startActivity(intent)

        }

        val btnVerEstatistica = findViewById<Button>(R.id.btnVerEstatistica)
        btnVerEstatistica.setOnClickListener {
            val intent = Intent(this@Jogos, Estatisticas::class.java)
            intent.putExtra("tdsJogos", tdsJogos.size.toString())
            intent.putExtra("idTournaments", idTournaments.toString())
            startActivity(intent)

        }
    }

    inner class JogosAdapter : BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_jogos,parent,false)
            val textApresentarn1 = rowView.findViewById<TextView>(R.id.TextNameEquipa1)
            val textApresentarn2 = rowView.findViewById<TextView>(R.id.TextNameEquipa2)

            textApresentarn1.text = listaJogos[position].TeamOneName
            textApresentarn2.text = listaJogos[position].TeamTwoName


           /* rowView.setOnClickListener {
                val intent = Intent(this@Jogadores, DetailRowJogadores::class.java)
                intent.putExtra("idPlayer", listaJogadores[position].idPlayer)
                startActivity(intent)
            }*/
           /* val imgbuttonDelete = rowView.findViewById<ImageView>(R.id.imageViewDeleteJ)
            imgbuttonDelete.setOnClickListener{
                val intent = Intent(this@Jogadores, Jogadores::class.java)
                VolleyHelper.instance.deleteIdJogadores(this@Jogadores, listaJogadores[position].idPlayer.toString()){
                    if(it){
                        Toast.makeText(applicationContext, this@Jogadores.getString(R.string.add_jogadorfailed), Toast.LENGTH_LONG).show()
                    } else{
                        Toast.makeText(applicationContext, this@Jogadores.getString(R.string.add_jogadorfailed), Toast.LENGTH_LONG).show()
                    }
                }
                startActivity(intent)
            }*/
            return rowView
        }
        override fun getItem(position: Int): Any {
            return listaJogos[position]
        }
        override fun getItemId(position: Int): Long {
            return 0
        }
        override fun getCount(): Int {
            return listaJogos.size
        }


    }
}