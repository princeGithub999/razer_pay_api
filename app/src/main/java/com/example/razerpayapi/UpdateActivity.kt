package com.example.razerpayapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.razerpayapi.databinding.ActivityUpdateBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityUpdateBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val name = intent.getStringExtra("name").toString()
        val email = intent.getStringExtra("email")
        id = intent.getStringExtra("id").toString()
        val contect = intent.getStringExtra("contact")


        binding.userNameUpdateEditText.setText(name)
        binding.userEmailUpdateEditText.setText(email)
        binding.userContactUpdateEditText.setText(contect)

        binding.UpdateButton.setOnClickListener {
            updateData()
        }

    }

    @SuppressLint("CheckResult")
    private fun updateData() {
        val apiKey = "rzp_test_AIjY27p4Xs3vsD"
        val apiSecret = "RW2r2y9zzh9eYzahp8cP0QPZ"
        val authenticator = "Basic " + android.util.Base64.encodeToString(
            "$apiKey:$apiSecret".toByteArray(),
            android.util.Base64.NO_WRAP
        )
        val headerMap = HashMap<String, String>()
        headerMap["Authorization"] = authenticator

        val name = binding.userNameUpdateEditText.text.toString()
        val email = binding.userEmailUpdateEditText.text.toString()
        val contect = binding.userContactUpdateEditText.text.toString()


        val updateData = UpdateDataModal(name, email, contect)

         RazorPayInterFase.createRetroFit().updateCustomers(headerMap,updateData,id)
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeOn(Schedulers.io())
             .subscribe({ result ->
                 Toast.makeText(this, "Update success", Toast.LENGTH_SHORT).show()
                finish()
            }, { error ->
                android.util.Log.e("RxJava", "An error occurred: ${error.message}", error)
                android.widget.Toast.makeText(
                    this,
                    "An error occurred: ${error.message}",
                    android.widget.Toast.LENGTH_SHORT
                ).show()
            })

    }
}