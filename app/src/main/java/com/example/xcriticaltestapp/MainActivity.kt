package com.example.xcriticaltestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private val textViewEmailError by lazy { findViewById<TextView>(R.id.textViewEmailError) }
    private val textViewPasswordError by lazy { findViewById<TextView>(R.id.textViewPasswordError) }
    private val textViewForgotPassword by lazy { findViewById<TextView>(R.id.textViewForgotPassword) }
    private val editTextEmailAddress by lazy { findViewById<EditText>(R.id.editTextEmailAddress) }
    private val editTextPassword by lazy { findViewById<EditText>(R.id.editTextPassword) }
    private val buttonLogin by lazy { findViewById<Button>(R.id.buttonLogin) }
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        buttonLogin.setOnClickListener {
            viewModel.validateEmail(editTextEmailAddress.text.toString()).observe(this, {
                if(it) {
                    textViewEmailError.visibility = View.VISIBLE
                }
            })
            viewModel.validatePassword(editTextPassword.text.toString()).observe(this, {
                if(it) {
                    textViewPasswordError.visibility = View.VISIBLE
                }
            })

            if(!textViewEmailError.isVisible && !textViewPasswordError.isVisible){
                val intent = Intent(this, MainTeleprompterActivity::class.java)
                startActivity(intent)
            }
        }

        textViewForgotPassword.setOnClickListener {
            val intent = Intent(this, RecoverPasswordActivity::class.java)
            startActivity(intent)
        }

        editTextEmailAddress.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                textViewEmailError.visibility = View.INVISIBLE
            }
        })

        editTextPassword.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                textViewPasswordError.visibility = View.INVISIBLE
            }
        })
    }
}