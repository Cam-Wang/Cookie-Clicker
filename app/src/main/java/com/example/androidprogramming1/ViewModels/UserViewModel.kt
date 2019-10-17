package com.example.androidprogramming1.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidprogramming1.CountRepo

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CountRepo(application.applicationContext)
    fun getUserCount(name:String) = repository.getUserCount(name)
    fun setUserCount(name:String,count:Long)=repository.setUserCount(name,count)
    val counter: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }



}
