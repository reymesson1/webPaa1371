package com.example.myapplication

import android.util.Log
import com.example.myapplication.Model.Master
import com.example.myapplication.Model.MasterService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RestAPI{

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
        newMaster.name = name

        var json = Gson().toJson(newMaster)

        var masterService = retrofit.create(MasterService::class.java)

        var call = masterService.getMaster()

        call.enqueue(object : Callback<List<Master>> {
            override fun onFailure(call: Call<List<Master>>, t: Throwable) {
                Log.i("error", t.toString())
            }

            override fun onResponse(call: Call<List<Master>>, response: Response<List<Master>>) {
                Log.i("response", response.body().toString())
            }

        })

    }



}