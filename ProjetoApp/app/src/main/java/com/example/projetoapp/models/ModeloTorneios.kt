package com.example.projetoapp.models

import org.json.JSONObject

class ModeloTorneios {

    var idTournaments : Long? = null
    var name : String? = null
    var prize: String? = null
    var initial_date : String? = null
    var last_date : String? = null


    fun toJson () : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("idTournaments", idTournaments)
        jsonObject.put("name", name)
        jsonObject.put("prize", prize)
        jsonObject.put("initial_date", initial_date)
        jsonObject.put("last_date", last_date)
        return  jsonObject
    }

    companion object {
        fun parseJson(jsonArticle: JSONObject) : ModeloTorneios {
            val torneio = ModeloTorneios ()

            torneio.idTournaments = jsonArticle.getLong("idTournaments")
            torneio.name = jsonArticle.getString("name")
            torneio.prize = jsonArticle.getString("prize")
            torneio.initial_date = jsonArticle.getString("initial_date")
            torneio.last_date = jsonArticle.getString("last_date" )

            return torneio
        }
    }
}