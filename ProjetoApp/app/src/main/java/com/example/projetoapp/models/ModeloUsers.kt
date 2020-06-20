package com.example.projetoapp.models

import org.json.JSONObject

class ModeloUsers {

    var username : String? = null
    var password : String? = null
    var name : String? = null
    var email : Int? = null

    fun toJson () : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("username", username)
        jsonObject.put("password", password)
        jsonObject.put("name", name)
        jsonObject.put("email", email)

        return  jsonObject
    }

    companion object {
        fun parseJson(jsonArticle: JSONObject) : ModeloUsers {
            val user = ModeloUsers ()

            user.username = jsonArticle.getString("username")
            user.password = jsonArticle.getString("password")
            user.name = jsonArticle.getString("name")
            user.email = jsonArticle.getInt("email")

            return user
        }
    }
}