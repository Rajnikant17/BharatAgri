package com.example.bharatagri.repository

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsArticle (
    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String?=null,
    @SerializedName("source")
    @Expose
    var source: NewsSource?,
    @SerializedName("title")
    @Expose
    var title: String?,
    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String?,
    @SerializedName("description")
    @Expose
     var description: String?,
    @SerializedName("content")
    @Expose
    var content: String?, )

data class NewsArticleResponse (
    @SerializedName("status")
    @Expose
    var status: String?,
    @SerializedName("articles")
    @Expose
    var articles: List<NewsArticle?>?
)

class NewsSource (
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("name")
    @Expose
    var name: String
)