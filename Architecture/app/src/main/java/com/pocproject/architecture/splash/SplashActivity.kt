package com.pocproject.architecture.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pocproject.architecture.R
import com.pocproject.architecture.login.LoginActivity
import com.pocproject.architecture.splash.ui.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        viewModel.splashFinished.observe(this, Observer {
            if (it == true) {
                navigateToNextScreen()
            }
        })

        viewModel.startSplashScreen()
    }

    private fun navigateToNextScreen() {
        // Navigate to the next screen here
        startActivity(Intent(this@SplashActivity,LoginActivity::class.java))

    }

}