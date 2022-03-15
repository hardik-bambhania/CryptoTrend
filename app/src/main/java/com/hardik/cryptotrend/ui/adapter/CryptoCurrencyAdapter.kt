package com.hardik.cryptotrend.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cryptotrend.common.model.CryptoCurrency
import com.hardik.cryptotrend.databinding.ItemCryptoCurrencyBinding
import com.hardik.cryptotrend.utils.roundOff

class CryptoCurrencyAdapter() :
    ListAdapter<CryptoCurrency, CryptoCurrencyAdapter.CurrencyViewHolder>(DiffCallback) {

    class CurrencyViewHolder(val binding: ItemCryptoCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding =
            ItemCryptoCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = getItem(position)
        with(holder.binding) {
            imgCurrency.setImageResource(currency.icon)
            txtCurrencyName.text = currency.name
            txtCurrencyUsd.text = "$${currency.usd.roundOff(2)}"
            txtCurrencyBtc.text = "${currency.btc.roundOff(6)} BTC"
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CryptoCurrency>() {
        override fun areItemsTheSame(oldItem: CryptoCurrency, newItem: CryptoCurrency): Boolean {
            return oldItem.id.equals(newItem.id, true)
        }

        override fun areContentsTheSame(oldItem: CryptoCurrency, newItem: CryptoCurrency): Boolean {
            return oldItem.equals(newItem)
        }
    }
}