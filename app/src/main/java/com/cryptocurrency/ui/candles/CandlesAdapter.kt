package com.cryptocurrency.ui.candles

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cryptocurrency.R
import com.cryptocurrency.data.model.Candle

class CandlesAdapter : ListAdapter<Candle, CandlesAdapter.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Candle>() {
            override fun areItemsTheSame(old: Candle, new: Candle) = old.timestamp == new.timestamp
            override fun areContentsTheSame(old: Candle, aNew: Candle) = old == aNew
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_currency, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, index: Int) = holder.bind(getItem(index)!!)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.findViewById<TextView>(R.id.pair)

        fun bind(candle: Candle) {
            textView.text = candle.detail
        }
    }
}