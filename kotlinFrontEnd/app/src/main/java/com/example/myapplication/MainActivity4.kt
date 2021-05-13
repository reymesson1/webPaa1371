package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.Model.Master
import com.example.myapplication.Model.Statitics
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main4.*
import kotlinx.android.synthetic.main.layout_item.view.*
import kotlinx.android.synthetic.main.layout_item_metrics.view.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity4 : AppCompatActivity() {

    var restApi = RestAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        doAsync {

            activityUiThread {

                Thread.sleep(1000)

                getData()
                getDataStatitics()
            }
        }


        btn_home_on_metrics.setOnClickListener{

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


    }

    fun getDataStatitics(){

        var statiticsService = restApi.getStatiticsAPI()

        var call = statiticsService.getStatitics()

        call.enqueue(object : Callback<List<Statitics>>{
            override fun onFailure(call: Call<List<Statitics>>, t: Throwable) {
                Log.i("error", t.toString())
            }

            override fun onResponse(
                call: Call<List<Statitics>>,
                response: Response<List<Statitics>>
            ) {

                Log.i("response", response.body().toString())

            }

        })

    }

    fun getData(){

        var masterService = restApi.getMasterAPI()

        var call = masterService.getMaster()

        call.enqueue(object : Callback<List<Master>> {
            override fun onFailure(call: Call<List<Master>>, t: Throwable) {
                Log.i("error", t.toString())
            }

            override fun onResponse(call: Call<List<Master>>, response: Response<List<Master>>) {
                response.body()!!.forEach { at->

                    var item = layoutInflater.inflate(R.layout.layout_item_metrics, null)

//                    item.nameTXT_metrics_post.setText(at.name)
//
//                    item.nameTXT_metrics_comment.setText(at.user)

                    scContentMetrics.addView(item)

                }
            }
        })

    }


}