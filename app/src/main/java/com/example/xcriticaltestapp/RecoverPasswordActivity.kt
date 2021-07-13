package com.example.xcriticaltestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import java.util.regex.Pattern

class RecoverPasswordActivity : AppCompatActivity() {

    private val textViewEmailError by lazy { findViewById<TextView>(R.id.textViewEmailError) }
    private val editTextEmailAddress by lazy { findViewById<EditText>(R.id.editTextEmailAddress) }
    private val buttonRecoverPassword by lazy { findViewById<Button>(R.id.buttonRecoverPassword) }
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover_password)
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
        buttonRecoverPassword.setOnClickListener {
            viewModel.validateEmail(editTextEmailAddress.text.toString()).observe(this, {
                if(it) {
                    textViewEmailError.visibility = View.VISIBLE
                }
            })
        }

        editTextEmailAddress.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                textViewEmailError.visibility = View.INVISIBLE
            }
        })
    }
}