package com.example.myapplication.Model

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CommentServicePost{

    @POST("/addwalletandroidcomment")

    fun setComment(@Body data : JSONObject) : Call<String>

}