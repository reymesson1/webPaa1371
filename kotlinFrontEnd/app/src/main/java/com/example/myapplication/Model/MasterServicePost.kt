package com.example.myapplication.Model

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MasterServicePost{

    @POST("/addwalletandroid")

    fun setMaster(@Body data : JSONObject) : Call<String>

}