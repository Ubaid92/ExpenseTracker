package com.ubaid.expensetracker.data

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties()
data class TransactionData(
    val name: String = "",
    val amount: String = "",
    val date: String = ""
)
