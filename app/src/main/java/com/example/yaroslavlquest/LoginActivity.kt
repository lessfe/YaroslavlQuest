package com.example.yaroslavlquest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.example.yaroslavlquest.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private var binding: ActivityLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.loginBtn.setOnClickListener {
            if (binding!!.emailEt.text.toString().isEmpty() || binding!!.passwordEt.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(applicationContext, "Fields cannot be empty", Toast.LENGTH_SHORT)
                    .show()
            } else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding!!.emailEt.text.toString(),
                    binding!!.passwordEt.text.toString()
                )
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        }
                    }
            }
        }
        binding!!.goToRegisterActivityTv.setOnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    RegistetActivity::class.java
                )
            )
        }
    }
}