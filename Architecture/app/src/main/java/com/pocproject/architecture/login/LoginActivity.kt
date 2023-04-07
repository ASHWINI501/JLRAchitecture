package com.pocproject.architecture.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.pocproject.architecture.R
import com.pocproject.architecture.databinding.ActivityLoginBinding
import com.pocproject.architecture.home.HomeActivity
import com.pocproject.architecture.login.di.repository.UserRepository
import com.pocproject.architecture.login.ui.viewmodel.LoginViewModel
import com.pocproject.architecture.login.ui.viewmodel.LoginViewModelFactory
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // set up data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        // set up the view model and observe login success LiveData
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory(UserRepository()))
            .get(LoginViewModel::class.java)

        loginViewModel.loginSuccess.observe(this) { success ->
            if (success) {
                // Login was successful, navigate to next screen
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            } else {
                val snackbar = Snackbar
                    .make(
                        binding.layout,
                        "Please enter valid user name and password",
                        Snackbar.LENGTH_LONG
                    )
                    .setAction(
                        "Retry"
                    )  // If the Retry button is pressed, show the message using Toast
                    {
                        Toast
                            .makeText(
                                this@LoginActivity,
                                "Retry Clicked",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }

                snackbar.show()
            }

        }

        // Set up login button click listener
        binding.loginBtn.setOnClickListener {
            val email = binding.loginUsernameEditText.text.toString()
            val password = binding.loginPasswordEditText.text.toString()
            loginViewModel.loginUser(email, password)
        }

    }
}