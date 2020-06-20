package com.example.projetoapp.models

import org.json.JSONObject

class ModeloSedes {

    var idVenues : Int? = null
    var name_venue : String? = null
    var location_venues : String? = null

    fun toJson () : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("idVenues", idVenues)
        jsonObject.put("name_venue", name_venue)
        jsonObject.put("location_venues", location_venues)
        return  jsonObject
    }

    companion object {
        fun parseJson(jsonArticle: JSONObject) : ModeloSedes {
            val sedes = ModeloSedes ()

            sedes.idVenues = jsonArticle.getInt("idVenues")
            sedes.name_venue = jsonArticle.getString("name_venue")
            sedes.location_venues = jsonArticle.getString("location_venues")

            return sedes
        }
    }
}