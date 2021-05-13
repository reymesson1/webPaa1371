package com.example.myapplication.Model

import retrofit2.Call
import retrofit2.http.POST

interface StatiticsService{

    @POST("/walletandroidstatitics")

    fun getStatitics() : Call<List<Statitics>>

}