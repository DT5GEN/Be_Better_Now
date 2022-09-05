package com.deeppowercrew.bebetternow

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashscreenActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        timer = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("splash", "onTick() called with: millisUntilFinished = $millisUntilFinished")
            }

            override fun onFinish() {
                startActivity(Intent(this@SplashscreenActivity, MainActivity::class.java))
                Log.d("finish", "onFinish() called")
            }

        }.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel() // if the user immediately after the start of the application wants to exit
                       // the application , then the main activity will not be created
    }
}