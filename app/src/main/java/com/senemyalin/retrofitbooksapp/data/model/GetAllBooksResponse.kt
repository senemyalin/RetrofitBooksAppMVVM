package com.senemyalin.retrofitbooksapp.data.model

data class GetAllBooksResponse(
    val books: List<Book>?,
    val message: String?,
    val success: Int?
)