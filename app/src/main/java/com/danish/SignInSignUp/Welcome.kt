package com.danish.SignInSignUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danish.SignInSignUp.SignIn
import com.danish.SignInSignUp.databinding.ActivityWelcomeBinding

class Welcome : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(SignIn.KEY2)
        val mail = intent.getStringExtra(SignIn.KEY1)
        val id = intent.getStringExtra(SignIn.KEY3)

        binding.etWelcome.text = "Welcome $name"
        binding.etMail.text = "Mail : $mail"
        binding.etUnique.text = "UserId : $id"



    }
}