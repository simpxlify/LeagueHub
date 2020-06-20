package com.example.projetoapp

import org.json.JSONObject


class Article  {

    var author      : String? = null
    var title       : String? = null
    var description : String? = null
    var url         : String? = null
    var urlToImage  : String? = null
    var publishedAt : String? = null
    var content     : String? = null

    constructor(
        author: String?,
        title: String?,
        description: String?,
        url: String?,
        urlToImage: String?,
        publishedAt: String?,
        content: String?
    ) {
        this.author = author
        this.title = title
        this.description = description
        this.url = url
        this.urlToImage = urlToImage
        this.publishedAt = publishedAt
        this.content = content
    }

    constructor( ) {

    }

    companion object {
        fun parseJson(jsonArticle: JSONObject) : Article {
            val article = Article ()

            article.author      = jsonArticle.getString("author")
            article.content     = jsonArticle.getString("content")
            article.description = jsonArticle.getString("description")
            article.title       = jsonArticle.getString("title")
            article.publishedAt = jsonArticle.getString("publishedAt")
            article.url         = jsonArticle.getString("url")
            article.urlToImage  = jsonArticle.getString("urlToImage")

            return article
        }
    }

}