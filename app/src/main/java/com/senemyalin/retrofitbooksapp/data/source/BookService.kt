package com.senemyalin.retrofitbooksapp.data.source

import com.senemyalin.retrofitbooksapp.common.Constants.Endpoint.GET_ALL_BOOKS
import com.senemyalin.retrofitbooksapp.common.Constants.Endpoint.GET_BOOK_DETAIL
import com.senemyalin.retrofitbooksapp.data.model.GetAllBooksResponse
import com.senemyalin.retrofitbooksapp.data.model.GetBookDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET(GET_ALL_BOOKS)
    fun getAllBooks(): Call<GetAllBooksResponse>

    @GET(GET_BOOK_DETAIL)
    fun getBookDetail(
        @Query("id") id: Int
    ): Call<GetBookDetailResponse>
}