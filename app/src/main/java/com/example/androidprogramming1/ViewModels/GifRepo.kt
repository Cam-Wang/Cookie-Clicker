package com.example.androidprogramming1.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidprogramming1.GetData
import com.example.androidprogramming1.Gif
import com.example.androidprogramming1.GifResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GifRepo {
    private val gifCall = GetData.create()
    fun getGif():LiveData<Gif>{
        val data = MutableLiveData<Gif>()
        gifCall.getGif(
            tag = "water",
            apiKey = "qIG9VrmaZADyiVsl0IKywNt4f2AIWXR2"
        ).enqueue(object :Callback<GifResponse> {
            override fun onResponse(call: Call<GifResponse>, response: Response<GifResponse>) {
                data.value = response.body()?.data
            }

            override fun onFailure(call: Call<GifResponse>, t: Throwable) {
            }
        })
        return data
    }
}