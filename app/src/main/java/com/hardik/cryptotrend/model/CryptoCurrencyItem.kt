package com.hardik.cryptotrend.model

import com.hardik.cryptotrend.R

/**
 * List of crypto currency assets
 */
enum class CryptoCurrencyItem(val id: String, val icon: Int) {
    BITCOIN("bitcoin", R.drawable.ic_bitcoin),
    ETHEREUM("ethereum", R.drawable.ic_ethereum),
    BINANCE_COIN("binance-bitcoin", R.drawable.ic_binance),
    BAT("basic-attention-token", R.drawable.ic_bat)
}