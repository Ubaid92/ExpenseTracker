package com.ubaid.expensetracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaid.expensetracker.data.TransactionData
import com.ubaid.expensetracker.databinding.ListItemTransactionsBinding

class ListViewAdapter : RecyclerView.Adapter<ListViewAdapter.ViewHolder>() {
    var transactionsList = arrayListOf<TransactionData>()

    inner class ViewHolder(var binding: ListItemTransactionsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun hold(transactionsList: TransactionData) {
            binding.listAmount.text = transactionsList.amount
            binding.listName.text = transactionsList.name

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ListItemTransactionsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return transactionsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.hold(transactionsList[position])

    }
}