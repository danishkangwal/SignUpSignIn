package com.danish.SignInSignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.danish.SignInSignUp.Users
import com.danish.SignInSignUp.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSignup.setOnClickListener {

            val user = Users(
                binding.etName.text.toString(),
                binding.etMail.text.toString(),
                binding.etPassword.text.toString(),
                binding.etUsername.text.toString()
            )
            database = FirebaseDatabase.getInstance().getReference("Users")

            database.child(binding.etUsername.text.toString()).setValue(user).addOnSuccessListener {
                binding.etName.text?.clear()
                binding.etMail.text?.clear()
                binding.etPassword.text?.clear()
                binding.etUsername.text?.clear()
                Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "User not registered", Toast.LENGTH_SHORT).show()
            }
        }
        binding.etSignIn.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

    }
}