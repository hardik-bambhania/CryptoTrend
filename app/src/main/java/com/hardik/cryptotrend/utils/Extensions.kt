package com.hardik.cryptotrend.utils

fun Float.roundOff(denotes: Int): String {
    return String.format("%.${denotes}f", this)
}