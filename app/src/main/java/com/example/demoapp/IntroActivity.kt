package com.example.demoapp

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demoapp.auth.LoginActivity
import com.example.demoapp.databinding.ActivityIntroBinding
import com.google.firebase.auth.FirebaseAuth

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val auth = FirebaseAuth.getInstance()
        if(auth.currentUser!=null)
        {
            redirect("Main")
        }


        binding.getStarted.setOnClickListener {
            redirect("Login")
        }

    }

    private fun redirect(name : String)
    {

        val intent = when(name){
            "Login"-> Intent(this, LoginActivity::class.java)
            "Main"-> Intent(this,MainActivity::class.java)
            else -> throw Exception("no path exists")
        }
        startActivity(intent)
        finish()
    }
}