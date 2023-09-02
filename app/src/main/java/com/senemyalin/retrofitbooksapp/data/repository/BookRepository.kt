package com.senemyalin.retrofitbooksapp.data.repository

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.senemyalin.retrofitbooksapp.common.loadImage
import com.senemyalin.retrofitbooksapp.data.model.Book
import com.senemyalin.retrofitbooksapp.data.model.GetAllBooksResponse
import com.senemyalin.retrofitbooksapp.data.model.GetBookDetailResponse
import com.senemyalin.retrofitbooksapp.data.source.BookService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val service: BookService
) {

    val allBooksLiveData = MutableLiveData<List<Book>?>()
    val bookDetailLiveData = MutableLiveData<Book?>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val errorMessageLiveData = MutableLiveData<String>()

    fun getBooks() {
        loadingLiveData.value = true

        service.getAllBooks()
            .enqueue(object : Callback<GetAllBooksResponse> {
                override fun onResponse(
                    call: Call<GetAllBooksResponse>,
                    response: Response<GetAllBooksResponse>
                ) {
                    val result = response.body()?.books

                    if (result.isNullOrEmpty().not()) {
                        allBooksLiveData.value = result
                    } else {
                        allBooksLiveData.value = null
                    }

                    loadingLiveData.value = false
                }

                override fun onFailure(call: Call<GetAllBooksResponse>, t: Throwable) {
                    loadingLiveData.value = false
                    errorMessageLiveData.value = t.message.orEmpty()
                    Log.e("GetAllBooks", t.message.orEmpty())
                }
            })
    }

    fun getBookDetail(id: Int) {
        loadingLiveData.value = true

        service.getBookDetail(id)
            .enqueue(object : Callback<GetBookDetailResponse> {
                override fun onResponse(
                    call: Call<GetBookDetailResponse>,
                    response: Response<GetBookDetailResponse>
                ) {
                    val result = response.body()?.book

                    if (result != null) {
                        bookDetailLiveData.value = result
                    } else {
                        bookDetailLiveData.value = null
                    }

                    loadingLiveData.value = false
                }

                override fun onFailure(call: Call<GetBookDetailResponse>, t: Throwable) {
                    loadingLiveData.value = true
                    errorMessageLiveData.value = t.message.orEmpty()
                    Log.e("GetProducts", t.message.orEmpty())
                }
            })
    }
}