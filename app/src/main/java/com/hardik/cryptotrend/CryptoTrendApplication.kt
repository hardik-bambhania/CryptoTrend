package com.hardik.cryptotrend

import android.app.Application
import com.cryptotrend.common.logger.AppLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CryptoTrendApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppLogger.initLogger(this)
    }
}