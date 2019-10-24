package com.example.androidprogramming1.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidprogramming1.CountRepo

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CountRepo(application.applicationContext) //creates a repo in the viewmodel
    fun getUserCount(name:String) = repository.getUserCount(name) //calls repo method for geting user count
    fun setUserCount(name:String,count:Long)=repository.setUserCount(name,count) //calls repo method for setting user count
    val counter: MutableLiveData<Int> by lazy { //sets the counter to be a live data
        MutableLiveData<Int>()
    }



}
