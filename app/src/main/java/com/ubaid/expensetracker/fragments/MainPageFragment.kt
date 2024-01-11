package com.ubaid.expensetracker.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ubaid.expensetracker.R
import com.ubaid.expensetracker.databinding.MainPageFragmentBinding

class MainPageFragment:Fragment(R.layout.main_page_fragment) {
    lateinit var binding: MainPageFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainPageFragmentBinding.bind(view)
    }
}