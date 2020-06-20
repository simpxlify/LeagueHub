package com.example.projetoapp.models

import org.json.JSONObject

class ModeloEquipas {

    var idTeam : Long? = null
    var name : String? = null
    var initials : String? = null
    var location : String? = null
    var coach : String? = null
    var idTournaments : String? = null


    fun toJson () : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("idTeam", idTeam)
        jsonObject.put("name", name)
        jsonObject.put("initials", initials)
        jsonObject.put("location", location)
        jsonObject.put("coach", coach)
        jsonObject.put("idTournaments", idTournaments)

        return  jsonObject
    }

    companion object {
        fun parseJson(jsonArticle: JSONObject) : ModeloEquipas {
            val team = ModeloEquipas ()

            team.idTeam = jsonArticle.getLong("idTeam")
            team.name = jsonArticle.getString("name")
            team.initials = jsonArticle.getString("initials")
            team.location = jsonArticle.getString("location")
            team.coach = jsonArticle.getString("coach")
            team.idTournaments = jsonArticle.getString("idTournaments")

            return team
        }
    }
}