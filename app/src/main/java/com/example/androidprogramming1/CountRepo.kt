package com.example.androidprogramming1

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import me.ibrahimsn.library.LiveSharedPreferences
class  CountRepo(context: Context){
    private val prefernces: SharedPreferences
    private val liveSharedPreferences: LiveSharedPreferences
    init {
        prefernces = context.getSharedPreferences(PREFS,Context.MODE_PRIVATE)
        liveSharedPreferences = LiveSharedPreferences(prefernces)
    }

    fun setUserCount(name: String, count: Long) {
        prefernces.edit().putLong(name,count).apply()
    }
    fun getUserCount(name: String): LiveData<Long> =
        Transformations.map(liveSharedPreferences.listenMultiple(listOf(name), 0L)) {it[name]}

    companion object {
        private const val PREFS = "clickCounts"
    }
}