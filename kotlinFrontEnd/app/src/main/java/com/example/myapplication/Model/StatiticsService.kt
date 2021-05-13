package com.example.myapplication.Model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface StatiticsService{

    @GET("/walletandroidstatitics")

    fun getStatitics() : Call<List<Statitics>>

}