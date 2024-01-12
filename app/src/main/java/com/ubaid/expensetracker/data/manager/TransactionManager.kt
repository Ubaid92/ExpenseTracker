package com.ubaid.expensetracker.data.manager

import android.content.Context
import com.ubaid.expensetracker.data.TransactionData
//val context:Context
class TransactionManager() {
//    val sharedPrefManager = SharedPrefManager(context)
    var transactionListData = arrayListOf<TransactionData>()
    var totalSum = 0.0

    fun saveData(data:TransactionData){
        transactionListData.add(data)
        totalSum += data.amount.toDouble()
    }




//    fun showBalance()


}