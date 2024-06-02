package com.dongguk.telepigeon.feature.splash

import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.dongguk.telepigeon.feature.MainActivity
import com.dongguk.telepigeon.feature.databinding.ActivitySplashBinding
import com.dongguk.telpigeon.core.ui.base.BindingActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BindingActivity<ActivitySplashBinding>({ ActivitySplashBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        loadSplashScreen()
    }

    private fun loadSplashScreen() {
        lifecycleScope.launch {
            delay(SPLASH_SCREEN_DELAY_TIME)
            navigateToMain()
        }
    }

    private fun navigateToMain() {
        Intent(this@SplashActivity, MainActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }

    companion object {
        const val SPLASH_SCREEN_DELAY_TIME = 1500L
    }
}
