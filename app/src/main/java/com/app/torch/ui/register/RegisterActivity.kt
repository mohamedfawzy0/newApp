package com.app.torch.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.torch.R
import com.app.torch.databinding.ActivityRegisterBinding
import com.app.torch.base.BaseActivity
import com.app.torch.ui.main.MainActivity

class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory)[AuthenticationViewModel::class.java]
        initView()
        setUpObservers()
        setUpListeners()
    }

    private fun initView() {
    }

    private fun setUpObservers() {
    }

    private fun setUpListeners() {
        binding.registerBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}
