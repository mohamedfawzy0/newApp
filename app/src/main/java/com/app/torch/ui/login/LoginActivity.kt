package com.app.torch.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.torch.R
import com.app.torch.base.BaseActivity
import com.app.torch.databinding.ActivityLoginBinding
import com.app.torch.ui.main.MainActivity
import com.app.torch.ui.register.RegisterActivity

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]

        setUpObservers()
        setUpListeners()
    }

    private fun setUpObservers() {

    }

    private fun setUpListeners() {
        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.registerBtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
}
