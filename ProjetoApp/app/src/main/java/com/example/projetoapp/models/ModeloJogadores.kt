package com.example.projetoapp.models

import org.json.JSONObject

class ModeloJogadores {

    var idPlayer : Long? = null
    var first_name : String? = null
    var last_name : String? = null
    var age : String? = null
    var height : String? = null
    var country : String? = null
    var position : String? = null
    var idTeam : String? = null



    fun toJson () : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("idPlayer", idPlayer)
        jsonObject.put("first_name", first_name)
        jsonObject.put("last_name", last_name)
        jsonObject.put("age", age)
        jsonObject.put("height", height)
        jsonObject.put("country", country)
        jsonObject.put("position", position)
        jsonObject.put("idTeam", idTeam)
        return  jsonObject
    }

    companion object {
        fun parseJson(jsonArticle: JSONObject) : ModeloJogadores {
            val player = ModeloJogadores ()

            player.idPlayer = jsonArticle.getLong("idPlayer")
            player.first_name = jsonArticle.getString("first_name")
            player.last_name = jsonArticle.getString("last_name")
            player.age = jsonArticle.getString("age")
            player.height = jsonArticle.getString("height" )
            player.country = jsonArticle.getString("country")
            player.position = jsonArticle.getString("position" )
           player.idTeam = jsonArticle.getString("idTeam" )

            return player
        }
    }
}