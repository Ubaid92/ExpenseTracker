package com.ubaid.expensetracker.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubaid.expensetracker.data.TransactionData
import com.ubaid.expensetracker.data.manager.TransactionManager
import java.util.Date

class MainViewModel:ViewModel() {
     var transactionManager = TransactionManager()
     var accountBalanceLiveData = MutableLiveData<String>("0")
     var transactionsListLiveData = MutableLiveData<ArrayList<TransactionData>>()

     fun save(name:String, amount:String,){
          val date = System.currentTimeMillis().toString()

          transactionManager.saveData(TransactionData(name,amount,date))
          transactionsListLiveData.postValue(transactionManager.transactionListData)
          accountBalanceLiveData.postValue(transactionManager.totalSum.toString())
     }

     fun changeHeading(){

     }
}