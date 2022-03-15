package com.hardik.cryptotrend.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cryptotrend.common.model.CryptoCurrency
import com.hardik.cryptotrend.databinding.ItemCryptoCurrencyBinding
import com.hardik.cryptotrend.utils.roundOff

class CryptoCurrencyAdapter(private var currencyList: List<CryptoCurrency>) :
    RecyclerView.Adapter<CryptoCurrencyAdapter.CurrencyViewHolder>() {

    class CurrencyViewHolder(val binding: ItemCryptoCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding =
            ItemCryptoCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currencyList[position]
        with(holder.binding) {
            imgCurrency.setImageResource(currency.icon)
            txtCurrencyName.text = currency.name
            txtCurrencyUsd.text = "$${currency.usd.roundOff(2)}"
            txtCurrencyBtc.text = "${currency.btc.roundOff(6)} BTC"
        }
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    fun refresh(newList: List<CryptoCurrency>) {
        currencyList = newList
        notifyDataSetChanged()
    }
}