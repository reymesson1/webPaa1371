package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.Model.Master
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_add.view.*
import kotlinx.android.synthetic.main.layout_item.view.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var restApi = RestAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         restApi.username = intent.getStringExtra("username").toString()


        doAsync {

            activityUiThread {

                Thread.sleep(1000)

                getData()
            }
        }

        btn_metrics.setOnClickListener{

            var intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)

        }

        btn_add.setOnClickListener {

            var item = layoutInflater.inflate(R.layout.layout_add, null)

            var alertDialog = AlertDialog.Builder(this)

            alertDialog.setTitle("New Post")

            alertDialog.setView(item)

            alertDialog.setPositiveButton("Save", DialogInterface.OnClickListener { dialog, which ->

                restApi.setMasterAPI(item.editTXT.text.toString())
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            })

            alertDialog.show()

        }

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

                    var item = layoutInflater.inflate(R.layout.layout_item, null)

                    item.nameTXT2.setText(at.name)

                    item.nameTXT4.setText(at.user)

                    scContent.addView(item)

                    item.setOnClickListener {et->

                        var intent = Intent(this@MainActivity, MainActivity2::class.java)
                        intent.putExtra("detailsId", at.id)
                        intent.putExtra("details", at.name)

                        startActivity(intent)

                    }


                }
            }
        })

    }

}