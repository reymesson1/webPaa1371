package com.example.myapplication

import retrofit2.Retrofit

class RestAPI{

    var retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.0.221:8082")
        .addConverterFactory(Gson)
        .build()

}