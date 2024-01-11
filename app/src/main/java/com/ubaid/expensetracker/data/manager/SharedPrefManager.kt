package com.ubaid.expensetracker.data.manager

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaid.expensetracker.data.TransactionData

class SharedPrefManager(val context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences("ExpenseTracker", Context.MODE_PRIVATE)
    private val gson = Gson()
    fun saveTransactionData(data: List<TransactionData>) {
        sharedPref.edit().putString(TRANSACTION_DATA,gson.toJson(data)).apply()
    }

    fun getTransactionData() {
        return  gson.fromJson(sharedPref.getString(TRANSACTION_DATA, null),
            object : TypeToken<Array<TransactionData>>() {}.type)
    }

    companion object{
        const val TRANSACTION_DATA = "transaction_data"
    }
}