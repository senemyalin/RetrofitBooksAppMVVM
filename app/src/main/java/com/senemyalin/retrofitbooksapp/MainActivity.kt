package com.senemyalin.retrofitbooksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.senemyalin.retrofitbooksapp.data.RetrofitProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitProvider.provideRetrofit(this)
    }
}