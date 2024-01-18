package com.ubaid.expensetracker.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.ubaid.expensetracker.R
import com.ubaid.expensetracker.databinding.LogInFragmentBinding
import com.ubaid.expensetracker.fragments.MainPageFragment

class LogInFragment : Fragment(R.layout.log_in_fragment) {
    lateinit var binding: LogInFragmentBinding
    private lateinit var auth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LogInFragmentBinding.bind(view)
        auth = Firebase.auth

        binding.register.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, RegisterUserFragment())
                .commit()
        }

        binding.signIn.setOnClickListener {
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            requireActivity(),
                            "Sign in successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        val user = auth.currentUser
                        requireActivity().supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.container, MainPageFragment())
                            .commit()
                    } else {
                        Toast.makeText(
                            requireActivity(),
                            "Failed: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
        
    }
}