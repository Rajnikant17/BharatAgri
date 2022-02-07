package com.example.bharatagri.repository

import com.example.bharatagri.utils.BaseDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class NewsDataSource
@Inject
constructor(val apiServices: ApiServices) : BaseDataSource() {
    suspend fun getNewsList(country: String, pageSize: String, page: Int, apikey: String) = invoke { apiServices.getNewsList(country,pageSize,page,apikey) }
}