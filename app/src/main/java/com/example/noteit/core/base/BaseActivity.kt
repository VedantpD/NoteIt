package com.example.noteit.core.base


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

open class BaseActivity : AppCompatActivity() {

    private val PREFS_NAME = "MyPrefsFile"

    protected fun saveShared(key: String,value: String) {
        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    protected fun getShared(key : String): String {
        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(key, "") ?: ""
    }


    protected open fun showToast(message : String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    // TO DO
    protected open fun String.log(){
        Log.d("maini", this)
    }

    protected fun nextActivity(destinationActivity: Class<*>) {
        val intent = Intent(this, destinationActivity)
        startActivity(intent)
    }

//    TO DO : protected open fun nextActivity(activityClass: Class<T>){
//        startActivity(Intent(this,this::class.java))
//    }

}