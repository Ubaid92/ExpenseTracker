package com.ubaid.expensetracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.ubaid.expensetracker.databinding.ActivityMainBinding
import com.ubaid.expensetracker.fragments.MainPageFragment
import com.ubaid.expensetracker.login.LogInFragment
import com.ubaid.expensetracker.model.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var oneTapClient: SignInClient
    private var currentUser: FirebaseUser? = null
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        oneTapClient = Identity.getSignInClient(this)
        currentUser = auth.currentUser
        if (currentUser != null) {
            Toast.makeText(this, "Welcome back ${currentUser?.displayName}", Toast.LENGTH_SHORT)
                .show()
            binding.heading.text = currentUser?.displayName.toString()


        }

        setupListeners()
        supportFragmentManager.beginTransaction().replace(R.id.container, MainPageFragment())
            .commit()
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]


    }

    private fun setupListeners() {
        binding.logInBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LogInFragment())
                .addToBackStack("any")
                .commit()
        }
        binding.logoutBtn.setOnClickListener {
            auth.signOut()
            currentUser = null
            binding.logInBtn.visibility = View.VISIBLE
            binding.logoutBtn.visibility = View.GONE
            binding.heading.text = "Expense Tracker"
            Toast.makeText(this, "Logout Success", Toast.LENGTH_SHORT).show()
        }

        supportFragmentManager.addFragmentOnAttachListener { _, fragment ->
            Toast.makeText(this, "CurrentUser: " + currentUser, Toast.LENGTH_SHORT).show()
            when (fragment) {
                is MainPageFragment, is LogInFragment -> if (currentUser != null) {
                    binding.logInBtn.visibility = View.GONE
                    binding.logoutBtn.visibility = View.VISIBLE
                    binding.heading.text = currentUser?.displayName.toString()

                } else {
                    binding.logInBtn.visibility = View.VISIBLE
                    binding.logoutBtn.visibility = View.GONE
                }
            }
        }
//        supportFragmentManager.addOnBackStackChangedListener {
//            Toast.makeText(this, "CurrentUser: " + currentUser, Toast.LENGTH_SHORT).show()
//            when (supportFragmentManager.fragments.last()) {
//                is MainPageFragment, is LogInFragment -> if (currentUser != null) {
//                    binding.logInBtn.visibility = View.GONE
//                } else binding.logInBtn.visibility = View.VISIBLE
//            }
//        }
    }

    fun googleLogIn() {
        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(getString(R.string.server_client_id))
                    .setFilterByAuthorizedAccounts(true)
                    .build()
            )
            .build()
        oneTapClient.beginSignIn(signInRequest).addOnSuccessListener { result ->
            Log.d(LogInFragment.TAG, ">>Success ")
            ActivityCompat.startIntentSenderForResult(
                this,
                result.pendingIntent.intentSender, LogInFragment.REQ_CODE,
                null, 0, 0, 0, null
            )
        }.addOnFailureListener {
            Log.d(LogInFragment.TAG, "Failed123: " + it.message)

        }

    }

//
//    fun setTitle(title: String) {
//        binding.heading.text = title
//    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LogInFragment.REQ_CODE && resultCode == RESULT_OK) {
            val idToken = oneTapClient.getSignInCredentialFromIntent(data).googleIdToken

            when {
                idToken != null -> {
                    // Got an ID token from Google. Use it to authenticate
                    // with Firebase.
                    val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                    auth.signInWithCredential(firebaseCredential)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(LogInFragment.TAG, "signInWithCredential:success")
                                currentUser = auth.currentUser
                                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                                binding.logInBtn.visibility = View.GONE
                                supportFragmentManager.beginTransaction()
                                    .add(R.id.container, MainPageFragment()).commit()
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(
                                    LogInFragment.TAG,
                                    "signInWithCredential:failure",
                                    task.exception
                                )
                                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                }

                else -> {
                    // Shouldn't happen.
                    Log.d(LogInFragment.TAG, "No ID token!")
                }
            }
        } else {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    fun signIn(email: String, password: String) {

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT)
                .show()
        } else {

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Sign in successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        currentUser = auth.currentUser
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.container, MainPageFragment())
                            .commit()
                    } else {
                        Toast.makeText(
                            this,
                            "Failed: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}
