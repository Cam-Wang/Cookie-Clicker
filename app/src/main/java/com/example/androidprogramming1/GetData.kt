package com.example.androidprogramming1

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.gson.GsonConverterFactory

interface GetData {

    @GET("v1/gifs/random")
    fun getWeather(@Query("tag") tag: String,
                   @Query("api_key") apiKey:String,
                   @Query("rating") rating: String)
    companion object {
        fun create(): gifAPI {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("api.giphy.com")
                .build()
            return retrofit.create(gifAPI::class.java)
        }
    }
}