package com.example.xcriticaltestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val textViewEmailError by lazy { findViewById<TextView>(R.id.textViewEmailError) }
    val textViewPasswordError by lazy { findViewById<TextView>(R.id.textViewPasswordError) }
    val textViewForgotPassword by lazy { findViewById<TextView>(R.id.textViewForgotPassword) }
    val editTextEmailAddress by lazy { findViewById<EditText>(R.id.editTextEmailAddress) }
    val editTextPassword by lazy { findViewById<EditText>(R.id.editTextPassword) }
    val buttonLogin by lazy { findViewById<Button>(R.id.buttonLogin) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLogin.setOnClickListener {
            if(editTextEmailAddress.text.isEmpty()){
                textViewEmailError.visibility = View.VISIBLE
            }
            if(editTextPassword.text.isEmpty()){
                textViewPasswordError.visibility = View.VISIBLE
            }
        }

        textViewForgotPassword.setOnClickListener {

            val intent = Intent(this, RecoverPasswordActivity::class.java)
            startActivity(intent)
        }

        editTextEmailAddress.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                textViewEmailError.visibility = View.INVISIBLE
            }
        })

        editTextPassword.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                textViewPasswordError.visibility = View.INVISIBLE
            }
        })
    }
}