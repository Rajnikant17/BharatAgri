package com.example.bharatagri.repository

import com.example.bharatagri.utils.DataState

class NewsRepositoryImpl
constructor(val newsDataSource: NewsDataSource)
    :NewsRepository {
    override suspend fun getNewsList(
        country: String,
        pageSize: String,
        page: Int,
        apikey: String
    ): DataState<NewsArticleResponse?> {
        return newsDataSource.getNewsList(country,pageSize,page,apikey)
    }
}