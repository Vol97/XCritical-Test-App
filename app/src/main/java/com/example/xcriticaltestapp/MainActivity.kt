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
        initializeListeners()
        Log.d("LifecycleTest", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("LifecycleTest", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifecycleTest", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifecycleTest", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifecycleTest", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LifecycleTest", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifecycleTest", "onDestroy")
    }

    private fun initializeListeners() {
        binding.buttonLogin.setOnClickListener {
            viewModel.validateEmail(binding.editTextEmailAddress.text.toString()).observe(this, {
                if(it) {
                    binding.textViewEmailError.visibility = View.VISIBLE
                }
            })
            viewModel.validatePassword(binding.editTextPassword.text.toString()).observe(this, {
                if(it) {
                    binding.textViewPasswordError.visibility = View.VISIBLE
                }
            })

            if(!binding.textViewEmailError.isVisible && !binding.textViewPasswordError.isVisible){
                val intentMainTeleprompterActivity = Intent(this, MainTeleprompterActivity::class.java)
                startActivity(intentMainTeleprompterActivity)
            }
        }

        binding.textViewForgotPassword.setOnClickListener {
            val intent = Intent(this, RecoverPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.editTextEmailAddress.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.textViewEmailError.visibility = View.INVISIBLE
            }
        })

        binding.editTextPassword.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.textViewPasswordError.visibility = View.INVISIBLE
            }
        })
    }
}