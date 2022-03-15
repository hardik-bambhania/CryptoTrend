package com.hardik.cryptotrend.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hardik.cryptotrend.BuildConfig
import com.hardik.cryptotrend.R
import com.hardik.cryptotrend.databinding.ActivityMainBinding
import com.hardik.cryptotrend.ui.adapter.CryptoCurrencyAdapter
import com.hardik.cryptotrend.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        // Used to load the 'cryptotrend' library on application startup.
        init {
            System.loadLibrary("cryptotrend")
        }
    }

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val cryptoCurrencyAdapter = CryptoCurrencyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {

        // Handle the splash screen transition.
        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        addObserver()
        addListener()
    }

    private fun initUI() {
        binding.recyclerViewCurrency.adapter = cryptoCurrencyAdapter
    }

    private fun addListener() {
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_item_export_log -> {
                    exportLogs()
                    true
                }
                R.id.menu_item_logout -> {
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    private fun addObserver() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currencyData.collectLatest {
                    (binding.recyclerViewCurrency.adapter as CryptoCurrencyAdapter)
                        .submitList(it)
                }
            }
        }
    }


    private fun exportLogs() {
        val uri = FileProvider.getUriForFile(
            this,
            BuildConfig.APPLICATION_ID + ".provider",
            com.cryptotrend.common.logger.AppLogger.getLogFile()
        )
        val intent = ShareCompat.IntentBuilder(this)
            .setStream(uri)
            .setType("vnd.android.cursor.dir/email")
            .addEmailTo(arrayOf("hardik.p.bambhania@gmail.com"))
            .setSubject("CryptoTrend Application Log")
            .intent
            .setAction(Intent.ACTION_SEND)
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        startActivity(intent)
    }
}