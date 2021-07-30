package com.example.xcriticaltestapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.xcriticaltestapp.models.MainViewModel
import com.example.xcriticaltestapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        binding.mainViewModel = viewModel

        viewModel.isInvalidLiveDataEmail.observe(this, {
            if (it) {
                binding.textViewEmailError.visibility = View.VISIBLE
            }
        })

        viewModel.isInvalidLiveDataPassword.observe(this, {
            if (it) {
                binding.textViewPasswordError.visibility = View.VISIBLE
            }
        })

        viewModel.isPassedValidation.observe(this, {
            if (it) {
                val intentMainTeleprompterActivity =
                    Intent(this, MainTeleprompterActivity::class.java)
                startActivity(intentMainTeleprompterActivity)
            }
        })

        viewModel.isChangingEmailText.observe(this, {
            if (it) {
                binding.textViewEmailError.visibility = View.INVISIBLE
            }
        })

        viewModel.isChangingPasswordText.observe(this, {
            if (it) {
                binding.textViewPasswordError.visibility = View.INVISIBLE
            }
        })

        binding.textViewForgotPassword.setOnClickListener {
            val intent = Intent(this, RecoverPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}