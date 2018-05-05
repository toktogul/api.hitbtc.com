package com.cryptocurrency.ui.candles

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.widget.LinearLayout
import com.cryptocurrency.R
import com.cryptocurrency.data.model.Symbol
import com.cryptocurrency.ui.list.SYMBOL_ID

import kotlinx.android.synthetic.main.activity_candles.*
import org.koin.android.ext.android.inject

class CandlesActivity : AppCompatActivity() {

    private lateinit var viewModel: CandlesViewModel
    private lateinit var adapter: CandlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candles)
        setSupportActionBar(toolbar)

        adapter = CandlesAdapter()
        list.adapter = adapter
        list.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

        val symbol = intent.getParcelableExtra<Symbol>("symbol")
        title = "${symbol.baseCurrency} -> ${symbol.quoteCurrency}"

        viewModel = inject<CandlesViewModel> { mapOf(SYMBOL_ID to symbol.id) }.value
        viewModel.candles().observe(this, Observer(adapter::submitList))
    }

}
