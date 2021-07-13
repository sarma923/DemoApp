package com.example.demoapp.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.demoapp.DetailsActivity
import com.example.demoapp.MainActivity
import com.example.demoapp.R
import com.example.demoapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.button2.setOnClickListener {
            signUpUser()
        }

        binding.login.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun signUpUser()
    {
        val email = binding.email2.text.toString()
        val password = binding.password2.text.toString()
        val confirmPassword = binding.editTextTextPassword2.text.toString()

        if(email.isBlank() || password.isBlank() || confirmPassword.isBlank())
        {
            Toast.makeText(this,"Please enter your email and password", Toast.LENGTH_SHORT).show()
        }

        if(password!=confirmPassword)
        {
            Toast.makeText(this,"Password and Confirm password doesn't match", Toast.LENGTH_SHORT).show()
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
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