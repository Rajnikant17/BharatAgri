package com.example.bharatagri.repository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("/v2/top-headlines")
    suspend fun getNewsList(
        @Query("country") country: String,
        @Query("pageSize") pageSize: String,
        @Query("page") page: Int,
        @Query("apiKey") apikey: String
    ): Response<NewsArticleResponse>
}