package com.cryptocurrency.ui.list

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.widget.LinearLayout
import com.cryptocurrency.R
import com.cryptocurrency.data.model.Symbol
import com.cryptocurrency.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.viewModel

const val CURRENCY_ID = "currencyId"
const val SYMBOL_ID = "symbolId"

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = MainAdapter(this::onCurrencyClick)
        list.adapter = adapter
        list.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        viewModel.fetchSymbols().observe(this, Observer(adapter::submitList))
    }

    private fun onCurrencyClick(symbol: Symbol) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("symbol", symbol)
        startActivity(intent)
    }
}
