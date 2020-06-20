package com.example.projetoapp.models

import org.json.JSONObject

class ModeloAds {

    var idAds : Int? = null
    var title_ads : String? = null
    var txt_ads : String? = null

    fun toJson () : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("idAds", idAds)
        jsonObject.put("title_ads", title_ads)
        jsonObject.put("txt_ads", txt_ads)
        return  jsonObject
    }

    companion object {
        fun parseJson(jsonArticle: JSONObject) : ModeloAds {
            val ads = ModeloAds ()

            ads.idAds = jsonArticle.getInt("idAds")
            ads.title_ads = jsonArticle.getString("title_ads")
            ads.txt_ads = jsonArticle.getString("txt_ads")

            return ads
        }
    }
}