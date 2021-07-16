package com.example.xcriticaltestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.xcriticaltestapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.mainViewModel = viewModel

        viewModel.isInvalidLiveDataEmail.observe(this, {
            if(it) {
                binding.textViewEmailError.visibility = View.VISIBLE
            }
        })

        viewModel.isInvalidLiveDataPassword.observe(this, {
            if(it) {
                binding.textViewPasswordError.visibility = View.VISIBLE
            }
        })

        viewModel.isPassedValidation.observe(this, {
            if(it) {
                val intentMainTeleprompterActivity = Intent(this, MainTeleprompterActivity::class.java)
                startActivity(intentMainTeleprompterActivity)
            }
        })

        viewModel.isChangingEmailText.observe(this, {
            if(it){
                binding.textViewEmailError.visibility = View.INVISIBLE
            }
        })

        viewModel.isChangingPasswordText.observe(this, {
            if(it){
                binding.textViewPasswordError.visibility = View.INVISIBLE
            }
        })

        binding.textViewForgotPassword.setOnClickListener {
            val intent = Intent(this, RecoverPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}