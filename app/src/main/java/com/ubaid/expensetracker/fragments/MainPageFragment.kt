package com.ubaid.expensetracker.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ubaid.expensetracker.MainActivity
import com.ubaid.expensetracker.R
import com.ubaid.expensetracker.adapter.ListViewAdapter
import com.ubaid.expensetracker.data.TransactionData
import com.ubaid.expensetracker.databinding.MainPageFragmentBinding
import com.ubaid.expensetracker.model.MainViewModel

class MainPageFragment : Fragment(R.layout.main_page_fragment) {
    private lateinit var binding: MainPageFragmentBinding
    private lateinit var mainViewModel: MainViewModel
    val time = System.currentTimeMillis()
    var listViewAdapter = ListViewAdapter(true)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainPageFragmentBinding.bind(view)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mainViewModel.syncData()

        mainViewModel.transactionsListLiveData.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = listViewAdapter
            binding.recyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireActivity(),
                    RecyclerView.VERTICAL
                )
            )
            listViewAdapter.transactionsList = it.reversed()
        }
        mainViewModel.accountBalanceLiveData.observe(viewLifecycleOwner) { balance ->
            binding.accountBalance.text = balance
        }



        showMoreListener()

        binding.btnAdd.setOnClickListener {
            performTransaction(OP_PLUS)


        }
        binding.btnSub.setOnClickListener {
            performTransaction(OP_MINUS)
        }


    }

    private fun performTransaction(operation: String) {
        val name = binding.itemName.text.toString()
        val amount = binding.itemAmount.text.toString()


        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(amount)) {
            Toast.makeText(requireActivity(), "Please enter data", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(name)) {
            Toast.makeText(requireActivity(), "Please enter name", Toast.LENGTH_SHORT).show()

        } else if (TextUtils.isEmpty(amount)) {
            Toast.makeText(requireActivity(), "Please enter amount", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(requireActivity(), "Data Updated", Toast.LENGTH_SHORT).show()
            mainViewModel.save(name, operation + amount)
            mainViewModel.syncData()
            binding.itemName.text?.clear()
            binding.itemAmount.text?.clear()
        }
    }

    fun showMoreListener() {
        binding.showMore.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListPageFragment())
                .addToBackStack(ListPageFragment::class.java.simpleName)
                .commit()

        }
    }

    companion object {
        const val OP_PLUS = "+"
        const val OP_MINUS = "-"
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).setTitle("Expense Tracker")
    }
}