package com.ubaid.expensetracker.data.manager

import android.util.Log
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ubaid.expensetracker.data.TransactionData

class FirebaseTransactionManager {
    private val db = Firebase.firestore

    fun storeTransaction(transactionData: TransactionData) {
        db.collection("transactions")
            .add(transactionData)
            .addOnSuccessListener {
                Log.d("Firebase", "Success")
            }.addOnFailureListener {
                Log.d("Firebase", "Failed ${it.message}")
            }
    }

    fun fetchData(response: (List<TransactionData>) -> Unit) {
        db.collection("transactions").orderBy("date", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result ->
                response(result.toObjects(TransactionData::class.java))
            }.addOnFailureListener {
                Log.d("Firebase", "Error ${it.message}")
            }
    }
}