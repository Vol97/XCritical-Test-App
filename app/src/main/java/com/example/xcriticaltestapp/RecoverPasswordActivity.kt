package com.example.xcriticaltestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.xcriticaltestapp.databinding.ActivityRecoverPasswordBinding

class RecoverPasswordActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRecoverPasswordBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecoverPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initializeListeners()
        Log.d("LifecycleTest", "onCreate_RecoverPasswordActivity")
    }

    override fun onStart() {
        super.onStart()
        Log.d("LifecycleTest", "onStart_RecoverPasswordActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifecycleTest", "onResume_RecoverPasswordActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifecycleTest", "onPause_RecoverPasswordActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifecycleTest", "onStop_RecoverPasswordActivity")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LifecycleTest", "onRestart_RecoverPasswordActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifecycleTest", "onDestroy_RecoverPasswordActivity")
    }

    private fun initializeListeners() {
        binding.buttonRecoverPassword.setOnClickListener {
            viewModel.validateEmail(binding.editTextEmailAddress.text.toString()).observe(this, {
                if(it) {
                    binding.textViewEmailError.visibility = View.VISIBLE
                }
            })
        }

        binding.editTextEmailAddress.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.textViewEmailError.visibility = View.INVISIBLE
            }
        })
    }
}