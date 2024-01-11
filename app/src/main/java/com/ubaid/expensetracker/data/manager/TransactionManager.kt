package com.ubaid.expensetracker.data.manager

import android.content.Context
import com.ubaid.expensetracker.data.TransactionData

class TransactionManager(val context:Context) {
    val sharedPrefManager = SharedPrefManager(context)
    var transactionListData = arrayListOf<TransactionData>()
    var totalSum = 0.0

    fun saveData(data:TransactionData){
        transactionListData.add(data)
        totalSum += data.amount.toDouble()
    }


//    fun showBalance()


}