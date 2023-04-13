package com.example.exchange.api

import com.example.exchange.model.Exchange
import com.example.exchange.model.ExchangeDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ServiceApi {

    @GET("/currency_data/live")
    fun getServiceAPI(
        @Header("apikey") apikey: String?
    ): Call<ExchangeDto>


}