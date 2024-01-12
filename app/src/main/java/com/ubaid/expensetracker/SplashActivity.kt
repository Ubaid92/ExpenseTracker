package com.ubaid.expensetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ubaid.expensetracker.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
   private lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        animateZoomOut()
    }
    private fun startNewActivity() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

//    private fun animateZoomIn() {
//        binding.splashImage.animate().scaleX(30f).scaleY(30f).setDuration(250).withEndAction {
//
//        }.start()
//    }

    private fun animateZoomOut() {
        binding.splashImage.animate().scaleX(0.4f).scaleY(0.4f).setDuration(3000).withEndAction {
            startNewActivity()
        }.start()
    }

}
