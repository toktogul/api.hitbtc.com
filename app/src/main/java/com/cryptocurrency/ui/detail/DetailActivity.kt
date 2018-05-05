package com.cryptocurrency.ui.detail

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.cryptocurrency.R
import com.cryptocurrency.data.model.Symbol
import com.cryptocurrency.ui.candles.CandlesActivity
import com.cryptocurrency.ui.list.CURRENCY_ID
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var symbol: Symbol

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        symbol = intent.getParcelableExtra("symbol")
        viewModel = inject<DetailViewModel> { mapOf(CURRENCY_ID to symbol.baseCurrency) }.value

        viewModel.getCurrency().observe(this, Observer {
            title = it!!.fullName
            quoteCurrency.text = "Quote currency: ${symbol.quoteCurrency}"
            quantityIncrement.text = "Quantity increment: ${symbol.quantityIncrement}"
            tickSize.text = "Tick size: ${symbol.tickSize}"
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, Menu.FIRST, 0, "History")!!.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == Menu.FIRST) {
            val intent = Intent(this, CandlesActivity::class.java)
            intent.putExtra("symbol", symbol)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}