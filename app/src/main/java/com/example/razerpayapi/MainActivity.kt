package com.example.razerpayapi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
    @SuppressLint("CheckResult")

    private var userList = ArrayList<Item>()
    private lateinit var recycleView: RecyclerView
    private lateinit var postButton: ImageButton

    @SuppressLint("CheckResult", "NotifyDataSetChanged")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recycleView = findViewById(R.id.userRecycleView)
        postButton = findViewById(R.id.post_Button)
        getUserList()

        postButton.setOnClickListener {
            startActivity(Intent(this,AddDataActivity::class.java))
        }
    }


    override fun onResume() {
        super.onResume()
        getUserList()
    }

    @SuppressLint("CheckResult", "NotifyDataSetChanged")
    fun getUserList(){
        val apiKey = "rzp_test_AIjY27p4Xs3vsD"
        val apiSecret = "RW2r2y9zzh9eYzahp8cP0QPZ"

        val authenticator = "Basic " + android.util.Base64.encodeToString(
            "$apiKey:$apiSecret".toByteArray(),
            android.util.Base64.NO_WRAP
        )
        val headerMap = HashMap<String, String>()
        headerMap["Authorization"] = authenticator
        RazorPayInterFase.createRetroFit().getCustomers(headerMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                val allData = result.items
                userList.clear()
                if (allData != null) {
                    userList.addAll(allData)
                }
                val userAdaptre = UserListAdapter(this, userList)
                recycleView.layoutManager = LinearLayoutManager(this)
                recycleView.adapter = userAdaptre
                userAdaptre.notifyDataSetChanged()
            }
    }

}