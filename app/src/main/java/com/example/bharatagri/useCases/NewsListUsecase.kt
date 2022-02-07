package com.example.bharatagri.useCases

import com.example.myapiservicesmodule.di.Constants
import com.example.bharatagri.utils.DataState
import com.example.bharatagri.repository.NewsRepository
import com.example.bharatagri.repository.NewsArticleResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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


















//@Singleton
//class BusinessLogic
//@Inject
//constructor(val apiServices: ApiServices) {
//    suspend fun executeMovieList(page_no: Int): Flow<DataState<ResponseMovieList>> = flow {
//
//        emit(DataState.Loading)
//        try {
//            val modelMovieListResponse =
//                apiServices.getMovieList(
//                    Constants.key,
//                    Constants.movie_type,
//                    page_no,
//                    Constants.apiKey
//                )
//            emit(DataState.Success(modelMovieListResponse))
//        } catch (e: Exception) {
//            emit(DataState.Error(e))
//        }
//    }
//
//    suspend fun executeMovieDetails(imdb_id: String?): Flow<DataState<ResponseMovieDetails>> =
//        flow {
//            emit(DataState.Loading)
//            try {
//                val modelMovieDetailsResponse =
//                    apiServices.getMovieDetails(imdb_id, Constants.apiKey)
//                emit(DataState.Success(modelMovieDetailsResponse))
//            } catch (e: Exception) {
//                emit(DataState.Error(e))
//            }
//        }
//}