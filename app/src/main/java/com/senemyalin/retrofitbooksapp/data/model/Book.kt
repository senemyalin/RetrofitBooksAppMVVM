package com.senemyalin.retrofitbooksapp.data.model

data class Book(
    val author: String?,
    val id: Int?,
    val image_url: String?,
    val is_best_seller: Boolean?,
    val name: String?,
    val price: Double?,
    val publisher: String?
)