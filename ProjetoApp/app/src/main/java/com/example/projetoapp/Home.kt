package com.example.projetoapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.projetoapp.models.ModeloAds
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_sedes.*
import org.json.JSONObject
import java.net.MalformedURLException
import java.net.URL
import java.nio.charset.Charset

class Home : AppCompatActivity() {

    var articles: MutableList<Article> = ArrayList()
    private var articlesAdapter = ArticlesAdapter()

    var listaAds : MutableList<ModeloAds> = ArrayList()
    var adsAdapter : AdsAdapter? = null
     fun createNews (){

        object : AsyncTask<Void, Void, String>() {
            override fun doInBackground(vararg params: Void?): String {

                var url = URL(BASE_API + PATH + API_KEY)
                var urlContent = url.readText(Charset.defaultCharset())
                Log.d("lastestnews", urlContent)

                return urlContent
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)

                val jsonResult = JSONObject(result)
                var okResult = jsonResult.getString("status")
                if (okResult.compareTo("ok") == 0) {

                    val articleJsonArray = jsonResult.getJSONArray("articles")

                    articles.clear()

                    for (index in 0 until articleJsonArray.length()) {
                        val jsonArticle = articleJsonArray[index] as JSONObject
                        articles.add(Article.parseJson(jsonArticle))
                    }

                    articlesAdapter.notifyDataSetChanged()

                } else {
                    Toast.makeText(
                        this@Home,
                        "Erro ao descarregar noticias",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }.execute()

        listviewNotc.adapter = articlesAdapter
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeBtn = findViewById<ImageButton>(R.id.homeBtn)
        val torneiosBtn = findViewById<ImageButton>(R.id.torneiosBtn)
        val equipasBtn = findViewById<ImageButton>(R.id.equipasBtn)
        val jogadoresBtn = findViewById<ImageButton>(R.id.jogadoresBtn)
        val sedesBtn = findViewById<ImageButton>(R.id.sedesBtn)
        val imageViewSobreN = findViewById<ImageView>(R.id.imageViewSobreN)
        val imageViewAd = findViewById<ImageView>(R.id.imageViewAd)


        adsAdapter = AdsAdapter()
        listviewAds.adapter = adsAdapter

        VolleyHelper.instance.getListarAds(this ){response ->
            response?.let {
                for(index in 0 until it.length()){
                    val jsonPlayer = it[index] as JSONObject
                    listaAds.add(ModeloAds.parseJson(jsonPlayer))
                }
                adsAdapter?.notifyDataSetChanged()
            }
        }

        imageViewAd.setOnClickListener {
            val intent = Intent(this, DetailAds::class.java)
            intent.putExtra("idAds", listaAds.size)
            startActivity(intent)

        }


        imageViewSobreN.setOnClickListener {
            val intent = Intent(this, SobreNos::class.java)
            startActivity(intent)
        }
        homeBtn.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        torneiosBtn.setOnClickListener {
            val intent = Intent(this, Torneios::class.java)
            startActivity(intent)

        }

        equipasBtn.setOnClickListener {
            val intent = Intent(this, Equipas::class.java)
            startActivity(intent)
        }

        jogadoresBtn.setOnClickListener {
            val intent = Intent(this, Jogadores::class.java)
            startActivity(intent)
        }

        sedesBtn.setOnClickListener {
            val intent = Intent(this, Sedes::class.java)
            startActivity(intent)
        }


        createNews()
    }

    inner class AdsAdapter : BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_ads,parent,false)
            val textApresentarttad = rowView.findViewById<TextView>(R.id.textViewTitleAd)
            val textApresentartxtad = rowView.findViewById<TextView>(R.id.textViewTxtAd)



            textApresentarttad.text = listaAds[position].title_ads
            textApresentartxtad.text = listaAds[position].txt_ads

            return rowView
        }
        override fun getItem(position: Int): Any {
            return listaAds[position]
        }
        override fun getItemId(position: Int): Long {
            return 0
        }
        override fun getCount(): Int {
            return listaAds.size
        }


    }
     inner class ArticlesAdapter : BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view  = layoutInflater.inflate(R.layout.row_article, parent, false)

            val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
            val textViewDate  = view.findViewById<TextView>(R.id.textViewDate)
            val imageViewArticle = view.findViewById<ImageView>(R.id.imageView)

            textViewTitle.text = articles[position].title
            textViewDate.text  = articles[position].publishedAt

            object : AsyncTask<Void,Void, Bitmap>(){
                override fun doInBackground(vararg params: Void?): Bitmap? {

                    try {

                        val url = URL(articles[position].urlToImage)
                        val input = url.openStream()
                        val bmp = BitmapFactory.decodeStream(input)
                        return bmp
                    }
                    catch (e: MalformedURLException){
                        return null
                    }
                }

                override fun onPostExecute(result: Bitmap?) {
                    super.onPostExecute(result)
                    result?.let{

                        imageViewArticle.setImageBitmap(it)
                    }

                }

            }.execute()

            view.setOnClickListener {
                val intent = Intent(this@Home, ArticleDetailActivity::class.java)

                intent.putExtra("title", articles[position].title)
                intent.putExtra("url", articles[position].url)

                startActivity(intent)
            }

            return view
        }

        override fun getItem(position: Int): Any {
            return articles[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return articles.size
        }
    }

    companion object{

        var BASE_API = "https://newsapi.org/v2/"
        var PATH     = "top-headlines?country=pt&category=sports"
        var API_KEY  = "&apiKey=f1a2b15f14e3469096c1203d1c0350f7"
    }
}
