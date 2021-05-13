package com.example.myapplication

import android.util.Log
import com.example.myapplication.Model.Comment
import com.example.myapplication.Model.Master
import com.example.myapplication.Model.MasterService
import com.example.myapplication.Model.MasterServicePost
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RestAPI{

    var username = ""

    var retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.0.221:8082")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getMasterAPI() : MasterService {

        return retrofit.create(MasterService::class.java)
    }

    fun setMasterAPI(name:String){

        var newMaster = Master()
        newMaster.id = Date().time.toString()
        newMaster.date = Date().toString()
        newMaster.details = emptyArray()
        newMaster.name = name
        newMaster.user = username

        var json = Gson().toJson(newMaster)

        var masterServicePost = retrofit.create(MasterServicePost::class.java)

        var call = masterServicePost.setMaster(JSONObject(json))

        call.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("error", t.toString())
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("response", response.body().toString())
            }

        })


    }



}