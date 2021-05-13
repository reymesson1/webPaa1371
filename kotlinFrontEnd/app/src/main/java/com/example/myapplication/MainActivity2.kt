package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.Model.Master
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.layout_item_details.view.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {

    var restAPI = RestAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        doAsync {

            activityUiThread {

                Thread.sleep(2000)
                getData()
            }
        }

        btn_back.setOnClickListener {

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn_sendCommentDetail.setOnClickListener {

            restAPI.setCommentAPI(intent.getStringExtra("detailsId").toString(),editCommentDetail.text.toString())
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }

    fun getData(){

        var masterService = restAPI.getMasterAPI()

        var call = masterService.getMaster()

        call.enqueue(object : Callback<List<Master>> {
            override fun onFailure(call: Call<List<Master>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<List<Master>>, response: Response<List<Master>>) {

                response.body()!!.forEach {at ->

                    Log.i("response", at.name)

                    if(at.name == intent.getStringExtra("details").toString() ){

                        Log.i("response", at.name)

                        at.details.forEach { et->

                            var item = layoutInflater.inflate(R.layout.layout_item_details, null)

                            item.nameTXTDetail.setText(et.comment.toString())

                            scContent2.addView(item)

                        }

                    }

                }

            }

        })


    }





}