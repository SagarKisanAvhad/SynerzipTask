package com.sagar.mvvmbelalapp.ui.auth

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.sagar.mvvmbelalapp.R
import com.sagar.mvvmbelalapp.databinding.ActivityLoginBinding
import com.sagar.mvvmbelalapp.util.hide
import com.sagar.mvvmbelalapp.util.show
import com.sagar.mvvmbelalapp.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        //val viewModel = ViewModelProviders.of(this,AuthViewModel::class.java)

        val viewModel: AuthViewModel by viewModels()
        viewModel.authListener = this
        binding.viewmodel = viewModel
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            progress_bar.hide()
            toast(it)
        })
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast(message)
    }
}