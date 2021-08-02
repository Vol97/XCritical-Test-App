package com.example.xcriticaltestapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.xcriticaltestapp.models.MainViewModel
import com.example.xcriticaltestapp.databinding.ActivityRecoverPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecoverPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecoverPasswordBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecoverPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        binding.mainViewModel = viewModel

        viewModel.isInvalidLiveDataEmail.observe(this, {
            if (it) {
                binding.textViewEmailError.visibility = View.VISIBLE
            }
        })

        viewModel.isChangingEmailText.observe(this, {
            if (it) {
                binding.textViewEmailError.visibility = View.INVISIBLE
            }
        })
    }
}