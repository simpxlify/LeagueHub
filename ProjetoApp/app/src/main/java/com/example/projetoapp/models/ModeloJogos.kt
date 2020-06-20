package com.example.projetoapp.models

import org.json.JSONObject

class ModeloJogos {

    var idGame : Int? = null
    var idTeamOne : String? = null
    var idTeamTwo : String? = null
    var TeamOneName : String? = null
    var TeamTwoName : String? = null
    var GoalsTeamOne : Int? = null
    var GoalsTeamTwo : Int? = null
    var idTournaments : String? = null

    fun toJson () : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("idGame", idGame)
        jsonObject.put("idTeamOne", idTeamOne)
        jsonObject.put("idTeamTwo", idTeamTwo)
        jsonObject.put("TeamOneName", TeamOneName)
        jsonObject.put("TeamTwoName", TeamTwoName)
        jsonObject.put("GoalsTeamOne", GoalsTeamOne)
        jsonObject.put("GoalsTeamTwo", GoalsTeamTwo)
        jsonObject.put("idTournaments", idTournaments)
        return  jsonObject
    }

    companion object {
        fun parseJson(jsonArticle: JSONObject) : ModeloJogos {
            val game = ModeloJogos ()

            game.idGame = jsonArticle.getInt("idGame")
            game.idTeamOne = jsonArticle.getString("idTeamOne")
            game.idTeamTwo = jsonArticle.getString("idTeamTwo")
            game.TeamOneName = jsonArticle.getString("TeamOneName")
            game.TeamTwoName = jsonArticle.getString("TeamTwoName")
            game.GoalsTeamOne = jsonArticle.getInt("GoalsTeamOne")
            game.GoalsTeamTwo = jsonArticle.getInt("GoalsTeamTwo")
            game.idTournaments = jsonArticle.getString("idTournaments")

            return game
        }
    }
}