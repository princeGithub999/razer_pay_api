package com.example.razerpayapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.razerpayapi.databinding.ActivityAddDataBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddDataBinding
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submit.setOnClickListener {

            addUser()
        }
    }

    @SuppressLint("CheckResult")
    fun addUser(){
        val name = binding.userName.text.toString()
        val email = binding.userEmail.text.toString()
        val contact =  binding.userContact.text.toString()
        val postData = PostDataModal(
            contact, email, name,"12ABCDE2356F7GH","1", Notes("Tea, Earl Grey, Hot", "Tea, Earl Grey… decaf.")
        )
        val apiKey = "rzp_test_AIjY27p4Xs3vsD"
        val apiSecret = "RW2r2y9zzh9eYzahp8cP0QPZ"
        val authenticator = "Basic " + android.util.Base64.encodeToString(
            "$apiKey:$apiSecret".toByteArray(),
            android.util.Base64.NO_WRAP
        )
        val headerMap = HashMap<String, String>()
        headerMap["Authorization"] = authenticator
        RazorPayInterFase.createRetroFit().postCustomers(headerMap,postData)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { result ->
                    Toast.makeText(this, "${result.name}", Toast.LENGTH_SHORT).show()
                    finish()
                },
                { error ->
                    // Handle error
                    Log.e("RxJava", "An error occurred: ${error.message}", error)
                    Toast.makeText(
                        this, "An error occurred: ${error.message}", Toast.LENGTH_SHORT
                    ).show()
                }
            )
    }
}