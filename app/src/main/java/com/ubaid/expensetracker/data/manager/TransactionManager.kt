package com.ubaid.expensetracker.data.manager

import android.content.Context
import com.ubaid.expensetracker.data.TransactionData
//val context:Context
class TransactionManager() {
    private val firebaseTransactionManager = FirebaseTransactionManager()
    var transactionListData = arrayListOf<TransactionData>()
    var totalSum = 0.0

    fun saveData(data:TransactionData){
        transactionListData.add(data)
        firebaseTransactionManager.storeTransaction(data)
        totalSum += data.amount.toDouble()
    }

    fun getData(response:(List<TransactionData>)->Unit) = firebaseTransactionManager.fetchData(response)




//    fun showBalance()


}