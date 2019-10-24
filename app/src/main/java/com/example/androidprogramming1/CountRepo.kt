package com.example.androidprogramming1

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import me.ibrahimsn.library.LiveSharedPreferences
class  CountRepo(context: Context){
    private val prefernces: SharedPreferences //refrence to shared prefrences
    private val liveSharedPreferences: LiveSharedPreferences //a live shared prefrences used to wrap shared prefences with live data
    init {
        prefernces = context.getSharedPreferences(PREFS,Context.MODE_PRIVATE) //setting the shared prefrences
        liveSharedPreferences = LiveSharedPreferences(prefernces) //makes the live data shared prefrences
    }

    fun setUserCount(name: String, count: Long) {
        prefernces.edit().putLong(name,count).apply() //setting the user counter score in shared prefrences
    }
    fun getUserCount(name: String): LiveData<Long> =
        Transformations.map(liveSharedPreferences.listenMultiple(listOf(name), 0L)) {it[name]} //returns the shared prefrences long as a live data long

    companion object {
        private const val PREFS = "clickCounts" //const to call in getting the shared prefrences
    }
}