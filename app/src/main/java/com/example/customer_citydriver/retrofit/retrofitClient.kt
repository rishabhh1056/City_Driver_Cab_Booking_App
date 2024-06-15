package com.example.customer_citydriver.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitClient {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.mapbox.com/search/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Retrofit_interface::class.java)
}