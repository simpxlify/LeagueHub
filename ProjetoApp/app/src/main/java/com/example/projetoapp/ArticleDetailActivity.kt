package com.example.projetoapp

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_article_detail.*


class ArticleDetailActivity : AppCompatActivity() {

    var url : String? =  null
    var titleArticle :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        val bundle = intent.extras
        bundle?.let {
            url = it.getString("url")
            titleArticle = it.getString("title")
        }

        url?.let {
            webViewArticle.loadUrl(it)
        }

        titleArticle?.let {
            title = it
        }

        webViewArticle.webViewClient = MyWebClient()

    }


    inner class MyWebClient : WebViewClient() {

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest? ): Boolean {
            request?.let {
                view?.loadUrl(it.url.toString())
            }
            return true
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            url?.let {
                view?.loadUrl(it)
            }
            return true
        }
    }
}
