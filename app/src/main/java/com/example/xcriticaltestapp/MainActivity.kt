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
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private val textViewEmailError: TextView by lazy { findViewById<TextView>(R.id.textViewEmailError) }
    private val textViewPasswordError by lazy { findViewById<TextView>(R.id.textViewPasswordError) }
    private val textViewForgotPassword by lazy { findViewById<TextView>(R.id.textViewForgotPassword) }
    private val editTextEmailAddress by lazy { findViewById<EditText>(R.id.editTextEmailAddress) }
    private val editTextPassword by lazy { findViewById<EditText>(R.id.editTextPassword) }
    private val buttonLogin by lazy { findViewById<Button>(R.id.buttonLogin) }
    private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
            if(editTextEmailAddress.text.isNullOrEmpty() || !validateEmail(editTextEmailAddress.text.toString())){
                textViewEmailError.visibility = View.VISIBLE
            }
            if(editTextPassword.text.isNullOrEmpty()){
                textViewPasswordError.visibility = View.VISIBLE
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

    private fun validateEmail(email: String) = EMAIL_ADDRESS_PATTERN.matcher(email).matches()
}