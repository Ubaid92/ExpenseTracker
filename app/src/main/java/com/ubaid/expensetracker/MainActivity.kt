package com.ubaid.expensetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ubaid.expensetracker.databinding.ActivityMainBinding
import com.ubaid.expensetracker.fragments.MainPageFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().add(R.id.container, MainPageFragment()).commit()
    }
}