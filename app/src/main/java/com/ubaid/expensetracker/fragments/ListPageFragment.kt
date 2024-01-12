package com.ubaid.expensetracker.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ubaid.expensetracker.MainActivity
import com.ubaid.expensetracker.R
import com.ubaid.expensetracker.adapter.ListViewAdapter
import com.ubaid.expensetracker.data.TransactionData
import com.ubaid.expensetracker.databinding.ListPageFragmentBinding
import com.ubaid.expensetracker.model.MainViewModel

class ListPageFragment : Fragment(R.layout.list_page_fragment) {
    private lateinit var binding: ListPageFragmentBinding
    private var listViewAdapter = ListViewAdapter(false)
    private lateinit var mainViewModel: MainViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListPageFragmentBinding.bind(view)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mainViewModel.transactionsListLiveData.observe(viewLifecycleOwner){
            listViewAdapter.transactionsList = it
        }

        binding.recyclerView.adapter = listViewAdapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                RecyclerView.VERTICAL
            )
        )

    }
    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).setTitle("Transaction History")
    }
}