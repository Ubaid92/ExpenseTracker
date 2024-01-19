package com.ubaid.expensetracker.login

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.ubaid.expensetracker.MainActivity
import com.ubaid.expensetracker.R
import com.ubaid.expensetracker.databinding.LogInFragmentBinding
import com.ubaid.expensetracker.fragments.MainPageFragment

@Suppress("DEPRECATION")
class LogInFragment : Fragment(R.layout.log_in_fragment) {
    private lateinit var binding: LogInFragmentBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var oneTapClient: SignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LogInFragmentBinding.bind(view)






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

        binding.googleSignIn.setOnClickListener {
            (requireActivity() as MainActivity).googleLogIn()
        }

    }


    companion object {
        val TAG = LogInFragment::class.java.simpleName
        val REQ_CODE = 1999190
    }
}