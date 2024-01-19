package com.ubaid.expensetracker.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ubaid.expensetracker.MainActivity
import com.ubaid.expensetracker.R
import com.ubaid.expensetracker.databinding.LogInFragmentBinding

@Suppress("DEPRECATION")

class LogInFragment : Fragment(R.layout.log_in_fragment) {
    private lateinit var binding: LogInFragmentBinding


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
            (requireActivity() as MainActivity).signIn(email, password)
        }

        binding.googleSignIn.setOnClickListener {
            (requireActivity() as MainActivity).googleLogIn()
        }


    }


    companion object {
        val TAG = LogInFragment::class.java.simpleName
        val REQ_CODE = 123
    }
}