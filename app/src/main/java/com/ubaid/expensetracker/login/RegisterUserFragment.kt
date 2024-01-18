package com.ubaid.expensetracker.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.ubaid.expensetracker.R
import com.ubaid.expensetracker.databinding.RegisterUserFragmentBinding

class RegisterUserFragment : Fragment(R.layout.register_user_fragment) {
    lateinit var binding: RegisterUserFragmentBinding
    private lateinit var auth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RegisterUserFragmentBinding.bind(view)
        auth = Firebase.auth

        binding.signUp.setOnClickListener {
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()
            val repeatPassword = binding.repeatPasswordText.text.toString()

            if (email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                Toast.makeText(requireActivity(), "Please fill in all fields", Toast.LENGTH_SHORT)
                    .show()
            } else if (password != repeatPassword) {
                Toast.makeText(
                    requireActivity(),
                    "Password not match try again",
                    Toast.LENGTH_SHORT
                ).show()
                binding.passwordText.setText("")
                binding.repeatPasswordText.setText("")
            } else {

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                requireActivity(),
                                "Your account is created",
                                Toast.LENGTH_LONG
                            ).show()
                            requireActivity().supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.container, LogInFragment())
                                .commit()
                        } else {
                            Toast.makeText(
                                requireActivity(),
                                "Failed: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }

        binding.signIn.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, LogInFragment())
                .commit()
        }
    }
}