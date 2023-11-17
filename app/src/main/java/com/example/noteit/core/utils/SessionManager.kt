package com.example.noteit.core.utils

import android.content.Context

class SessionManager(context: Context){
    private val sharedPrefs = context.getSharedPreferences("Auth", Context.MODE_PRIVATE)

    fun getStatus() : Boolean{
        return sharedPrefs.getBoolean("Status",false)
    }
    fun setStatus(value : Boolean){
        sharedPrefs.edit().putBoolean("Status",value).apply()
    }
    fun setCount(count: Int) {
        sharedPrefs.edit().putInt("COUNT_KEY", count).apply()
        sharedPrefs.edit().apply()
    }
    fun getCount():Int {
        return sharedPrefs.getInt("COUNT_KEY",-1)
    }
}