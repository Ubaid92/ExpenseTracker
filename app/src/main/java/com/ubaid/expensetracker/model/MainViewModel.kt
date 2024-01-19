package com.ubaid.expensetracker.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubaid.expensetracker.data.TransactionData
import com.ubaid.expensetracker.data.manager.TransactionManager

class MainViewModel : ViewModel() {
    var transactionManager = TransactionManager()
    var accountBalanceLiveData = MutableLiveData<String>("0")
    var transactionsListLiveData = MutableLiveData<List<TransactionData>>()

    fun save(name: String, amount: String) {
        val date = System.currentTimeMillis().toString()
        transactionManager.saveData(TransactionData(name, amount, date))
        accountBalanceLiveData.postValue(transactionManager.totalSum.toString())
    }

    fun syncData(){
        transactionManager.getData {
            accountBalanceLiveData.postValue(it.sumOf { tData ->
                tData.amount.toDouble()
            }.toString())
            transactionsListLiveData.postValue(it)
        }
    }

    fun changeHeading() {

    }
}