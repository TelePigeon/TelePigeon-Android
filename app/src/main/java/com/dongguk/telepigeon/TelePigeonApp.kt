package com.dongguk.telepigeon

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.dongguk.telepigeon.BuildConfig.KAKAO_NATIVE_APP_KEY
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility

@HiltAndroidApp
class TelePigeonApp : Application() {
    override fun onCreate() {
        super.onCreate()

        setTimber()
        setDarkMode()
        setKakao()
    }

    private fun setTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun setKakao() {
        KakaoSdk.init(this, KAKAO_NATIVE_APP_KEY)
    }
}
