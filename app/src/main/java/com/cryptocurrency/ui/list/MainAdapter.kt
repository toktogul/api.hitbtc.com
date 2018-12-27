package com.cryptocurrency.ui.list

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cryptocurrency.R
import com.cryptocurrency.data.model.Symbol

class MainAdapter(private val onCurrencyClick: (Symbol) -> Unit) :
        PagedListAdapter<Symbol, MainAdapter.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Symbol>() {
            override fun areItemsTheSame(old: Symbol, new: Symbol) = old.id == new.id
            override fun areContentsTheSame(old: Symbol, new: Symbol) = old == new
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_currency, parent, false))
        viewHolder.itemView.setOnClickListener {
            val pos = viewHolder.adapterPosition
            if (pos != -1) onCurrencyClick(getItem(pos)!!)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, index: Int) = holder.bind(getItem(index)!!)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.findViewById<TextView>(R.id.pair)

        fun bind(symbol: Symbol) {
            textView.text = "${symbol.baseCurrency}/${symbol.quoteCurrency}"
        }
    }
}