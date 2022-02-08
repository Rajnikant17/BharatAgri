package com.example.bharatagri.useCases

import com.example.myapiservicesmodule.di.Constants
import com.example.bharatagri.utils.DataState
import com.example.bharatagri.repository.NewsRepository
import com.example.bharatagri.repository.NewsArticleResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ActivityRetainedScoped
class NewsListUsecase
@Inject
constructor(val newsRepository: NewsRepository) {
    suspend fun executeMovieList(currentPage: Int): Flow<DataState<NewsArticleResponse?>> = flow {
        emit(DataState.Loading)
        val response= newsRepository.getNewsList(
            Constants.country,
            Constants.pageSize,
            currentPage,
            Constants.apiKey
        )
        when (response.statusCode) {
            200 -> emit(DataState.Success(response.data, response.statusCode))
            else -> emit(DataState.Error(response.statusCode, null,response.errorMsg))
        }
    }
}