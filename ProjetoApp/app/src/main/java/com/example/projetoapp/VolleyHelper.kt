package com.example.projetoapp

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONObject


class VolleyHelper {

    private var queue : RequestQueue? = null


    fun userLogin (context: Context, username : String, password: String, loginEvent : ((Boolean)->Unit) ) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()
            jsonObject.put("username", username)
            jsonObject.put("password", password)

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST,
                BASE_API + USER_LOGIN,
                jsonObject,
                Response.Listener{
                    Log.d("VolleyHelper", it.toString())
                    if (it.getBoolean("auth")){
                        token = it.getString("token")
                        loginEvent.invoke(true)
                    }else {
                        loginEvent.invoke(false)
                    }

                },
                Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    loginEvent.invoke(false)
                }
            )
            queue!!.add(jsonObjectRequest)
        }
    }

    fun userRegister (context: Context, username : String, name :String, email:String, password: String,  registerEvent : ((Boolean)->Unit) ) {
        doAsync {
            queue = Volley.newRequestQueue(context)


            val jsonObject = JSONObject()
            jsonObject.put("username",username)
            jsonObject.put("name",name)
            jsonObject.put("email",email)
            jsonObject.put("password", password)


            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST,
                BASE_API + USER_REGISTER,
                jsonObject,
                Response.Listener{
                    Log.d("VolleyHelper", it.toString())

                    registerEvent.invoke(true)


                },
                Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    registerEvent.invoke(false)
                }
            )
            queue!!.add(jsonObjectRequest)
        }
    }

    fun getListarAds(context: Context, adsEvent : ((JSONArray?)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + ADS,
                Response.Listener{
                    adsEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    adsEvent.invoke(null)
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    fun addAds(context: Context, idAds: String, title_ads: String, txt_ads : String, adsEvent : ((Boolean)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()
            jsonObject.put("idAds", idAds)
            jsonObject.put("title_ads", title_ads)
            jsonObject.put("txt_ads", txt_ads)

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                BASE_API + ADS ,
                jsonObject,
                Response.Listener {
                    adsEvent.invoke(true)
                    Log.d("VolleyHelper", it.toString())
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())

                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }

            queue!!.add(jsonObjectRequest)
        }
    }


    fun getListarSedes(context: Context, sedesEvent : ((JSONArray?)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + SEDES,
                Response.Listener{
                    sedesEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    sedesEvent.invoke(null)
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    fun addSedes(context: Context, idVenues: String, name_venue: String, location_venues : String, sedesEvent : ((Boolean)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()
            jsonObject.put("idVenues", idVenues)
            jsonObject.put("name_venue", name_venue)
            jsonObject.put("location_venues", location_venues)

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                BASE_API + SEDES ,
                jsonObject,
                Response.Listener {
                    sedesEvent.invoke(true)
                    Log.d("VolleyHelper", it.toString())
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())

                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }

            queue!!.add(jsonObjectRequest)
        }
    }


    fun getListarIdJogos(context: Context, idTournaments: String, jogosEvent : ((JSONArray?)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + JOGOS + "/" + idTournaments,
                Response.Listener{
                    jogosEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    jogosEvent.invoke(null)
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    fun getListarJogos(context: Context, jogosEvent : ((JSONArray?)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + JOGOS,
                Response.Listener{
                    jogosEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    jogosEvent.invoke(null)
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    fun addJogo (context: Context, idGame: String, idTeamOne: String, idTeamTwo: String, TeamOneName: String, TeamTwoName: String, GoalsTeamOne: String, GoalsTeamTwo: String, idTournaments: String, jogosEvent: ((Boolean) -> Unit)) {

        doAsync {

            queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()

            jsonObject.put("idGame", idGame)
            jsonObject.put("idTeamOne", idTeamOne)
            jsonObject.put("idTeamTwo", idTeamTwo)
            jsonObject.put("TeamOneName", TeamOneName)
            jsonObject.put("TeamTwoName", TeamTwoName)
            jsonObject.put("GoalsTeamOne", GoalsTeamOne)
            jsonObject.put("GoalsTeamTwo", GoalsTeamTwo)
            jsonObject.put("idTournaments", idTournaments)

            val jsonObjectRequest = object : JsonObjectRequest(

                Method.POST,
                BASE_API + JOGOS,
                jsonObject,
                Response.Listener {

                    jogosEvent.invoke(true)
                    Log.d("VolleyHelper", it.toString())
                },
                Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                }
            ) {

                override fun getHeaders(): MutableMap<String, String> {

                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }

            queue!!.add(jsonObjectRequest)
        }
    }


    fun getListarEquipas(context: Context, equipasEvent : ((JSONArray?)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + EQUIPAS,
                Response.Listener{
                    equipasEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    equipasEvent.invoke(null)
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    fun getListarIdEquipa(context: Context, idTeam: String, equipasEvent : ((JSONArray?)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + EQUIPAS + "/" + idTeam,
                Response.Listener{
                    equipasEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    equipasEvent.invoke(null)
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    fun deleteIdTorneios(context: Context, idTournaments: String, equipasEvent: ((Boolean)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.DELETE,
                BASE_API + TORNEIOS + "/" + idTournaments,
                Response.Listener {
                    Log.d("VolleyHelper", it.toString())
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())

                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }


    fun deleteIdSedes(context: Context, idVenues: String, equipasEvent: ((Boolean)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.DELETE,
                BASE_API + SEDES + "/" + idVenues,
                Response.Listener {
                    Log.d("VolleyHelper", it.toString())
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())

                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    fun deleteIdJogadores(context: Context, idPlayer: String, equipasEvent: ((Boolean)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.DELETE,
                BASE_API + JOGADORES + "/" + idPlayer,
                Response.Listener {
                    Log.d("VolleyHelper", it.toString())
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())

                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    fun deleteIdEquipa(context: Context, idTeam: String, equipasEvent: ((Boolean)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.DELETE,
                BASE_API + EQUIPAS + "/" + idTeam,
                Response.Listener {
                    Log.d("VolleyHelper", it.toString())
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())

                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    fun getListarIdJogadores(context: Context, idPlayer: String, jogadoresEvent : ((JSONArray?)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + JOGADORES + "/" + idPlayer,
                Response.Listener{
                    jogadoresEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    jogadoresEvent.invoke(null)
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    fun getListarIdTorneio(context: Context, idTournaments: String, torneiosEvent : ((JSONArray?)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + TORNEIOS + "/" + idTournaments,
                Response.Listener{
                    torneiosEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    torneiosEvent.invoke(null)
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    fun addEquipas(context: Context, idTeam: String, name: String, initials : String, location: String, coach: String,idTournaments: String, equipasEvent : ((Boolean)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()
            jsonObject.put("idTeam", idTeam)
            jsonObject.put("name", name)
            jsonObject.put("initials", initials)
            jsonObject.put("location", location)
            jsonObject.put("coach", coach)
            jsonObject.put("idTournaments", idTournaments)



            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                BASE_API + EQUIPAS ,
                jsonObject,
                Response.Listener {
                    equipasEvent.invoke(true)
                    Log.d("VolleyHelper", it.toString())
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())

                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }

            queue!!.add(jsonObjectRequest)
        }
    }

    fun addEquipasNoTorneio(context: Context, idTeam: String, name: String, initials : String, location: String, coach: String,idTournaments: String, equipasEvent : ((Boolean)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()
            jsonObject.put("idTeam", idTeam)
            jsonObject.put("name", name)
            jsonObject.put("initials", initials)
            jsonObject.put("location", location)
            jsonObject.put("coach", coach)
            jsonObject.put("idTournaments", idTournaments)


            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                BASE_API + EQUIPAS2 ,
                jsonObject,
                Response.Listener {
                    equipasEvent.invoke(true)
                    Log.d("VolleyHelper", it.toString())
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())

                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }

            queue!!.add(jsonObjectRequest)
        }
    }

    fun getListarTorneios(context: Context, torneiosEvent : ((JSONArray?)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + TORNEIOS,
                Response.Listener{
                    torneiosEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    torneiosEvent.invoke(null)
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    fun addTorneios(context: Context, idTournaments: String, name: String, prize : String, initial_date: String, last_date: String,  torneiosEvent : ((Boolean)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()
            jsonObject.put("idTournaments", idTournaments)
            jsonObject.put("name", name)
            jsonObject.put("prize", prize)
            jsonObject.put("initial_date", initial_date)
            jsonObject.put("last_date", last_date)

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                BASE_API + TORNEIOS , jsonObject,
                Response.Listener {
                    torneiosEvent.invoke(true)
                    Log.d("VolleyHelper", it.toString())
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())

                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }

            queue!!.add(jsonObjectRequest)
        }
    }


    fun getListarJogadores(context: Context, jogadoresEvent : ((JSONArray?)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + JOGADORES,
                Response.Listener{
                    jogadoresEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    jogadoresEvent.invoke(null)
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }


    fun addJogadores(context: Context, idPlayer: String, first_name: String, last_name : String, age: String, height: String, country: String, position: String,idTeam: String,   jogadoresEvent : ((Boolean)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()
            jsonObject.put("idPlayer", idPlayer)
            jsonObject.put("first_name", first_name)
            jsonObject.put("last_name", last_name)
            jsonObject.put("age", age)
            jsonObject.put("height", height)
            jsonObject.put("country", country)
            jsonObject.put("position", position)
            jsonObject.put("idTeam", idTeam)


            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                BASE_API + JOGADORES ,
                jsonObject,
                Response.Listener {
                    jogadoresEvent.invoke(true)
                    Log.d("VolleyHelper", it.toString())
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())

                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }

            queue!!.add(jsonObjectRequest)
        }
    }


    fun addJogadoresNaEquipa(context: Context, idPlayer: String, first_name: String, last_name : String, age: String, height: String, country: String, position: String, idTeam: String, jogadoresEvent : ((Boolean)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()
            jsonObject.put("idPlayer", idPlayer)
            jsonObject.put("first_name", first_name)
            jsonObject.put("last_name", last_name)
            jsonObject.put("age", age)
            jsonObject.put("height", height)
            jsonObject.put("country", country)
            jsonObject.put("position", position)
            jsonObject.put("idTeam", idTeam)

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                BASE_API + JOGADORES2 ,
                jsonObject,
                Response.Listener {
                    jogadoresEvent.invoke(true)
                    Log.d("VolleyHelper", it.toString())
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())

                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }

            queue!!.add(jsonObjectRequest)
        }
    }


    fun recoverPassword(context: Context, username: String, password : String,  torneiosEvent : ((Boolean)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)
            val jsonObject = JSONObject()
            jsonObject.put("password", password)

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.PUT,
                BASE_API + RECOVER + "/" + username,
                jsonObject,
                Response.Listener {
                    torneiosEvent.invoke(true)
                    Log.d("VolleyHelper", it.toString())
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type","application/json")
                    return map
                }
            }
            queue!!.add(jsonObjectRequest)
        }
    }

    companion object {

        const val  BASE_API = "http://0e63894e550b.ngrok.io"
        //const val  BASE_API = "http://192.168.1.80:3000"
        const val  USER_LOGIN = "/auth/login"
        const val  USER_REGISTER = "/auth/register"
        const val  RECOVER = "/auth/users"
        const val  JOGADORES = "/api/jogador"
        const val  JOGADORES2 = "/api/jogadoresnaequipa"
        const val  EQUIPAS = "/api/equipa"
        const val  EQUIPAS2 = "/api/equipasnotorneio"
        const val  TORNEIOS = "/api/torneio"
        const val  SEDES = "/api/sede"
        const val  JOGOS = "/api/jogos"
        const val  ADS = "/api/ads"



        var token = ""

        private var mInstance : VolleyHelper? = VolleyHelper()

        val instance : VolleyHelper
            @Synchronized get(){
                if(null == mInstance){
                    mInstance = VolleyHelper()
                }
                return mInstance!!
            }
    }
}