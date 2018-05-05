package com.cryptocurrency.data.model

import java.util.*

data class Candle(
        var timestamp: Date? = null,
        var open: String = "",
        var close: String = "",
        var min: String = "",
        var max: String = "",
        var volume: String = "",
        var volumeQuote: String = "",
        var detail: String = ""
)