package com.example.bharatagri.di

import com.example.bharatagri.repository.NewsDataSource
import com.example.bharatagri.repository.NewsRepository
import com.example.bharatagri.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
@Module
@InstallIn(ActivityRetainedComponent::class)
class NewsModule {
    @Provides
    @ActivityRetainedScoped
    fun provideRepository(newsDataSource: NewsDataSource): NewsRepository {
        return NewsRepositoryImpl(newsDataSource)
    }
}