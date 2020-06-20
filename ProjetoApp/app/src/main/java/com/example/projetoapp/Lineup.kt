package com.example.projetoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projetoapp.models.ModeloJogadores
import kotlinx.android.synthetic.main.activity_lineup.*
import org.json.JSONObject

class Lineup : AppCompatActivity() {

    var idTeam : String? = null
    var players : MutableList<ModeloJogadores> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lineup)

        val bundle = intent.extras
        bundle?.let{
            idTeam = it.getString("idTeam")
        }
        VolleyHelper.instance.getListarJogadores(this ){response ->
            response?.let {
                for(index in 0 until it.length()){
                    val jsonTeams = it[index] as JSONObject
                    players.add(ModeloJogadores.parseJson(jsonTeams))
                    if(idTeam== players[index].idTeam) {
                        when (players[index].position) {
                            AVANCADO -> {
                                textViewAvancado.text = players[index].first_name
                            }
                            GUARDAREDES -> {
                                textViewGuardaredes.text = players[index].first_name
                            }
                            AVANCADOESQUERDO -> {
                                textViewAvancadoEsq.text = players[index].first_name
                            }
                            AVANCADODIREITO -> {
                                textViewAvancadoDireito.text = players[index].first_name
                            }
                            MEDIOCENTRO -> {
                                textViewMeioCampo.text = players[index].first_name
                            }
                            MEDIOESQUERDO -> {
                                textViewMeioCampoE.text = players[index].first_name
                            }
                            MEDIODIREITO -> {
                                textViewMeioCampoD.text = players[index].first_name
                            }
                            DEFESACENTRALESQUERDO -> {
                                textViewDefesaE.text = players[index].first_name
                            }
                            DEFESACENTRALDIREITO -> {
                                textViewDefesaD.text = players[index].first_name
                            }
                            DEFESAESQUERDO -> {
                                textViewDefesaMeioE.text = players[index].first_name
                            }
                            DEFESADIREITO -> {
                                textViewDefesaMeioD.text = players[index].first_name
                            }
                        }
                    }
                }
            }
        }


    }

    companion object{
        val AVANCADO = "Avancado"
        val GUARDAREDES = "Guarda-Redes"
        val AVANCADOESQUERDO = "Avancado Esquerdo"
        val AVANCADODIREITO = "Avancado Direito"
        val MEDIOCENTRO = "Medio Centro"
        val MEDIOESQUERDO = "Medio Esquerdo"
        val MEDIODIREITO = "Medio Direito"
        val DEFESACENTRALESQUERDO = "Defesa Central Esquerdo"
        val DEFESACENTRALDIREITO = "Defesa Central Direito"
        val DEFESAESQUERDO = "Defesa Esquerdo"
        val DEFESADIREITO = "Defesa Direito"

    }

}