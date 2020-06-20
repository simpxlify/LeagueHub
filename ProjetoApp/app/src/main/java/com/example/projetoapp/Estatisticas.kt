package com.example.projetoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.projetoapp.models.ModeloEquipas
import com.example.projetoapp.models.ModeloJogos
import org.json.JSONObject
import kotlin.math.pow
import kotlin.math.sqrt

class Estatisticas : AppCompatActivity() {
    var totalHomeGoals : Float = 0.0f
    var totalAwayGoals : Float = 0.0f
    var averageGoalsTeam : Float = 0.0f

    var averageHomeGoals : Float = 0.0f
    var averageAwayGoals : Float = 0.0f

    var standardDeviationTeam : Float = 0.0f
    var standardDeviationHomeGoals : Float = 0.0f
    var standardDeviationAwayGoals : Float = 0.0f
    var standardDeviationGame : Float = 0.0f
    var averageGoalsGame : Float = 0.0f
    var totalGoals : Float = 0.0f
    var idTournaments : String? = null
    var tdsJogos: String? = null
    var allGames : MutableList<ModeloJogos> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estatisticas)


        val bundle = intent.extras
        bundle?.let {
            tdsJogos = it.getString("tdsJogos")
            idTournaments = it.getString("idTournaments")
        }



        val textViewTotalTeams = findViewById<TextView>(R.id.textViewTotalTeams)
        val textViewTotalGames = findViewById<TextView>(R.id.textViewTotalGames)
        val textViewTotalGoals = findViewById<TextView>(R.id.textViewTotalGoals)
        val textViewTotalHomeGoals = findViewById<TextView>(R.id.textViewTotalHomeGoals)
        val textViewTotalAwayGoals = findViewById<TextView>(R.id.textViewTotalAwayGoals)

        val textViewAverageGoalsTeam = findViewById<TextView>(R.id.textViewAverageGoalsTeam)
        val textViewAverageGoalsGame = findViewById<TextView>(R.id.textViewAverageGoalsGame)
        val textViewAverageHomeGoals = findViewById<TextView>(R.id.textViewAverageHomeGoals)
        val textViewAverageAwayGoals = findViewById<TextView>(R.id.textViewAverageAwayGoals)

        val textViewStandardDeviationTeam = findViewById<TextView>(R.id.textViewStandardDeviationTeam)
        val textViewStandardDeviationGame = findViewById<TextView>(R.id.textViewStandardDeviationGame)
        val textViewStandardDeviationHomeGoals = findViewById<TextView>(R.id.textViewStandardDeviationHomeGoals)
        val textViewStandardDeviationAwayGoals = findViewById<TextView>(R.id.textViewStandardDeviationAwayGoals)




        VolleyHelper.instance.getListarIdJogos(this, idTournaments.toString()) { response ->

            response?.let {

                for (index in 0 until it.length()) {

                    val gameJSON : JSONObject = it[index] as JSONObject
                    allGames.add(ModeloJogos.parseJson(gameJSON))


                    textViewTotalTeams.text = tdsJogos?.toInt()?.times(2).toString()
                    totalGoals += (allGames[index].GoalsTeamOne!! + allGames[index].GoalsTeamTwo!!)
                    totalHomeGoals += allGames[index].GoalsTeamOne!!
                    totalAwayGoals += allGames[index].GoalsTeamTwo!!

                    averageGoalsGame = totalGoals / allGames.size
                    averageHomeGoals = totalHomeGoals / allGames.size
                    averageAwayGoals = totalAwayGoals / allGames.size
                    averageGoalsTeam = totalGoals / textViewTotalTeams.text.toString().toInt()

                    textViewTotalGames.text = allGames.size.toString()
                    textViewTotalGoals.text = totalGoals.toString()
                    textViewTotalHomeGoals.text = totalHomeGoals.toString()
                    textViewTotalAwayGoals.text = totalAwayGoals.toString()

                    textViewAverageGoalsGame.text = "%.2f".format(averageGoalsGame)
                    textViewAverageHomeGoals.text = "%.2f".format(averageHomeGoals)
                    textViewAverageAwayGoals.text = "%.2f".format(averageAwayGoals)
                    textViewAverageGoalsTeam.text = "%.2f".format(averageGoalsTeam)

                }


                var difSqr : Float = 0.0f

                for (index in 0 until allGames.size) {
                    totalGoals = ((allGames[index].GoalsTeamOne!! + allGames[index].GoalsTeamTwo!!).toFloat())
                    difSqr += (totalGoals - averageGoalsGame).toDouble().pow(2.0).toInt()
                }
                standardDeviationGame = sqrt(difSqr / allGames.size)

                for (index in 0 until allGames.size) {
                    totalGoals = ((allGames[index].GoalsTeamOne!! + allGames[index].GoalsTeamTwo!!).toFloat())
                    difSqr += (totalGoals - averageGoalsTeam).toDouble().pow(2.0).toFloat()
                }
                standardDeviationTeam = sqrt(difSqr / allGames.size)

                for (index in 0 until allGames.size) {
                    totalHomeGoals = allGames[index].GoalsTeamOne!!.toFloat()
                    difSqr += (totalHomeGoals - averageHomeGoals).toDouble().pow(2.0).toInt()
                }
                standardDeviationHomeGoals = sqrt(difSqr / allGames.size)


                for (index in 0 until allGames.size) {
                    totalAwayGoals = allGames[index].GoalsTeamOne!!.toFloat()
                    difSqr += (totalAwayGoals - averageAwayGoals).toDouble().pow(2.0).toInt()
                }
                standardDeviationAwayGoals = sqrt(difSqr / allGames.size)

                textViewStandardDeviationTeam.text = "%.2f".format(standardDeviationTeam)
                textViewStandardDeviationGame.text = "%.2f".format(standardDeviationGame)
                textViewStandardDeviationHomeGoals.text = "%.2f".format(standardDeviationHomeGoals)
                textViewStandardDeviationAwayGoals.text = "%.2f".format(standardDeviationAwayGoals)
            }
        }
    }
}