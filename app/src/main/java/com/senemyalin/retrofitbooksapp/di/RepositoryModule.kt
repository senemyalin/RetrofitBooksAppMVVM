package com.senemyalin.retrofitbooksapp.di

import com.senemyalin.retrofitbooksapp.data.repository.BookRepository
import com.senemyalin.retrofitbooksapp.data.source.BookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBookRepository(service: BookService): BookRepository = BookRepository(service)
}