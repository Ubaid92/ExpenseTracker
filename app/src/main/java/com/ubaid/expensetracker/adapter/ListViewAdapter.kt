package com.ubaid.expensetracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.ubaid.expensetracker.R
import com.ubaid.expensetracker.data.TransactionData
import com.ubaid.expensetracker.databinding.ListItemTransactionsBinding
import com.ubaid.expensetracker.databinding.ListItemTransactionsHomeBinding
import com.ubaid.expensetracker.util.DateUtils

class ListViewAdapter(private val isLimited: Boolean) :
    RecyclerView.Adapter<ListViewAdapter.ViewHolder>() {
    var transactionsList = arrayListOf<TransactionData>()

    inner class ViewHolder(private val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun hold(transactionsList: TransactionData) {
            if (isLimited) {
                binding as ListItemTransactionsHomeBinding
                binding.listAmount.text = transactionsList.amount
                binding.listName.text = transactionsList.name
                binding.listDate.text = DateUtils.getFormattedTime(transactionsList.date)
                if (transactionsList.amount.contains("+")) {
                    binding.listAmount.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.green
                        )
                    )
                } else {
                    binding.listAmount.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.red
                        )
                    )
                }
            } else {
                binding as ListItemTransactionsBinding
                binding.listAmount.text = transactionsList.amount
                binding.listName.text = transactionsList.name
                binding.listDate.text = DateUtils.getFormattedTime(transactionsList.date)
                if (transactionsList.amount.contains("+")) {
                    binding.listAmount.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.green
                        )
                    )
                } else {
                    binding.listAmount.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.red
                        )
                    )
                }
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = if (isLimited) ListItemTransactionsHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        else ListItemTransactionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return if (isLimited && transactionsList.size >= 2) 2
        else transactionsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.hold(transactionsList[position])
    }
}