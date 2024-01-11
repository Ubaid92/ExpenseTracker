package com.ubaid.expensetracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.ubaid.expensetracker.R
import com.ubaid.expensetracker.databinding.ListPageFragmentBinding

class ListPageFragment:Fragment(R.layout.list_page_fragment) {
lateinit var binding: ListPageFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListPageFragmentBinding.bind(view)

    }
}