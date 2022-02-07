package com.example.bharatagri.repository
import com.example.bharatagri.utils.DataState

interface NewsRepository {
    suspend fun getNewsList(country: String, pageSize: String, page: Int, apikey: String): DataState<NewsArticleResponse?>
}