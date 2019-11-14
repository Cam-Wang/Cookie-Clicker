package com.example.androidprogramming1

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.gson.GsonConverterFactory

interface GetData {

    @GET("v1/gifs/random")
    fun getGif(    @Query("tag") tag: String,
                   @Query("api_key") apiKey:String): Call<GifResponse>
    companion object {
        fun create(): GetData {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://api.giphy.com")
                .build()
            return retrofit.create(GetData::class.java)
        }
    }
}