package com.example.CryptoGeek.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.CryptoGeek.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.example.CryptoGeek.ui.SignUpActivity as signup

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textViewIN.setOnClickListener {
            val intent = Intent(this, signup::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                }
            } else Toast.makeText(this, "Empty fields", Toast.LENGTH_SHORT).show()
        }
        firebaseAuth = FirebaseAuth.getInstance()
    }
}