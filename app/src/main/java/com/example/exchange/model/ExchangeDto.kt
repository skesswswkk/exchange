package com.example.exchange.model

import com.google.gson.annotations.SerializedName

data class ExchangeDto (
    @SerializedName("quotes")
//    val exchangeList: List<Exchange>
    val exchangeList: Exchange
)