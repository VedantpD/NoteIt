package com.example.noteit.ui.splashscreen

import android.os.Bundle
import android.util.Log
import com.example.noteit.ui.MainActivity
import com.example.noteit.core.base.BaseActivity
import com.example.noteit.core.utils.SessionManager
import com.example.noteit.databinding.ActivitySplashScreenBinding
import com.example.noteit.ui.authentication.LoginSignUpActivity


class SplashScreenActivity : BaseActivity() {

    private lateinit var binding : ActivitySplashScreenBinding
    private lateinit var sessionManager : SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        begin()
    }

    private fun begin() {
        val ThinkBot = "Note It!"
        val stringBuilder1 = StringBuilder()
        val t1 = Thread {
            for (letters in ThinkBot) {
                stringBuilder1.append(letters)
                Thread.sleep(250)
                runOnUiThread {
                    binding.txtTitle.text = stringBuilder1.toString()
                }
            }
            val status = sessionManager.getStatus()
            Log.d("authi",status.toString())
            if(status) {
                nextActivity(MainActivity::class.java)
            }else{
                nextActivity(LoginSignUpActivity::class.java)
            }
            finish()
        }.start()
    }

}