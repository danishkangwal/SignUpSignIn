package com.danish.SignInSignUp

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.danish.SignInSignUp.databinding.ActivitySignInBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    companion object{
        const val KEY1 = "com.danish.SignInSignUp.SignIn.mail"
        const val KEY2 = "com.danish.SignInSignUp.SignIn.name"
        const val KEY3 = "com.danish.SignInSignUp.SignIn.id"
    }
    private lateinit var binding : ActivitySignInBinding
    lateinit var database : DatabaseReference
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSignIn.setOnClickListener {
            if(binding.checkBox.isChecked) {
                val uniqueId = binding.usernameEt.text.toString()
                if (uniqueId.isNotEmpty()) {
                    readData(uniqueId)
                } else {
                    Toast.makeText(this, "Enter your username", Toast.LENGTH_LONG).show()
                }
            }
            else{
                binding.checkBox.buttonTintList = ColorStateList.valueOf(Color.RED)
                Toast.makeText(this,"Please accept the T&C",Toast.LENGTH_LONG).show()
            }
        }


    }

    private fun readData(uniqueId: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(uniqueId).get().addOnSuccessListener {
            //if user exist
            if(it.exists()){
                val email = it.child("email").value
                val name = it.child("name").value
                val userId = it.child("uniqueId").value

                val intentWelcome = Intent(this, Welcome::class.java)
                intentWelcome.putExtra(KEY1,email.toString())
                intentWelcome.putExtra(KEY2,name.toString())
                intentWelcome.putExtra(KEY3,userId.toString())
                startActivity(intentWelcome)
            }
            //if user do not exist
            else{
                Toast.makeText(this,"User does not exist",Toast.LENGTH_LONG).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }

    }
}