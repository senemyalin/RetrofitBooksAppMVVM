package com.senemyalin.retrofitbooksapp.data

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.senemyalin.retrofitbooksapp.common.Constants.BASE_URL
import com.senemyalin.retrofitbooksapp.data.source.BookService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitProvider{

    companion object{
        var bookService: BookService? = null

        private const val TIMEOUT = 60L

        fun provideRetrofit(context: Context) {
            val chuckerInterceptor = ChuckerInterceptor.Builder(context).build()

            val okHttpClient = OkHttpClient.Builder().apply {
                addInterceptor(chuckerInterceptor)
                readTimeout(TIMEOUT, TimeUnit.SECONDS)
                connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            }.build()

            bookService = Retrofit.Builder().apply {
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(BASE_URL)
                client(okHttpClient)
            }.build().create(BookService::class.java)
        }

    }
}