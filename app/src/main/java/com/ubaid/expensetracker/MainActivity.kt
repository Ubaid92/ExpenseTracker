package com.ubaid.expensetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ubaid.expensetracker.databinding.ActivityMainBinding
import com.ubaid.expensetracker.fragments.MainPageFragment
import com.ubaid.expensetracker.model.MainViewModel

class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding
   private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().add(R.id.container, MainPageFragment()).commit()
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

    }

    fun setTitle(title:String){

            binding.heading.text = title
    }
}