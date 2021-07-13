package com.example.demoapp.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.demoapp.DetailsActivity
import com.example.demoapp.MainActivity
import com.example.demoapp.R
import com.example.demoapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.button.setOnClickListener {
            loginUser()
        }

        binding.signup.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginUser(){

        val email = binding.email.text.toString()
        val password = binding.password.text.toString()


        if(email.isBlank() || password.isBlank())
        {
            Toast.makeText(this,"Please enter your email and password", Toast.LENGTH_SHORT).show()
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful)
            {
                Toast.makeText(this,"success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DetailsActivity::class.java)
                startActivity(intent)
                finish()
            }
            else
            {

            }
        }.addOnFailureListener {
            Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
        }
    }
}