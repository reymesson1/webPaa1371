package com.example.myapplication.Model

import retrofit2.Call
import retrofit2.http.GET

interface MasterService{

    @GET("/wallet")

    fun getMaster() : Call<List<Master>>

}