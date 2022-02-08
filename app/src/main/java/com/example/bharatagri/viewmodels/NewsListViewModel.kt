package com.example.bharatagri.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bharatagri.repository.NewsArticleResponse
import com.example.bharatagri.useCases.NewsListUsecase
import com.example.bharatagri.utils.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NewsListViewModel @ViewModelInject
constructor(application: Application, private val apiCallNewsListUsecase: NewsListUsecase) :
    AndroidViewModel(application) {
    private val movieMutableListLiveData: MutableLiveData<DataState<NewsArticleResponse?>> = MutableLiveData()
    val movieListLiveData: LiveData<DataState<NewsArticleResponse?>>
        get() = movieMutableListLiveData

    fun getNewsList(page:Int) {
        viewModelScope.launch {
                        apiCallNewsListUsecase.executeMovieList(page).onEach { dataState ->
                            movieMutableListLiveData.value = dataState
                            if(dataState.statusCode==200)
                                movieMutableListLiveData.value= DataState.Default
                        }.launchIn(viewModelScope)
        }
    }
}