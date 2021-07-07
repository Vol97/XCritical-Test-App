package com.example.xcriticaltestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class RecoverPasswordActivity : AppCompatActivity() {

    val textViewEmailError by lazy { findViewById<TextView>(R.id.textViewEmailError) }
    val editTextEmailAddress by lazy { findViewById<EditText>(R.id.editTextEmailAddress) }
    val buttonRecoverPassword by lazy { findViewById<Button>(R.id.buttonRecoverPassword) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover_password)

        buttonRecoverPassword.setOnClickListener {
            if(editTextEmailAddress.text.isEmpty()){
                textViewEmailError.visibility = View.VISIBLE
            }
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
    }
}