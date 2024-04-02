package com.example.razerpayapi

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RazorPayInterFase {


    @GET("customers")
    fun getCustomers(@HeaderMap headers: HashMap<String, String>): Observable<UserResponsivListModle>

    @POST("customers")
    fun postCustomers(@HeaderMap headers: HashMap<String,String>,@Body post: PostDataModal): Observable<PostDataModal>

    @PUT("customers/{id}")
    fun updateCustomers(
        @HeaderMap headers: HashMap<String, String>,@Body updateData: UpdateDataModal, @Path("id") id: String): Observable<UpdateDataModal>


    companion object Factory {
        fun createRetroFit(): RazorPayInterFase {
            val retroFit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.razorpay.com/v1/")
                .build()
                .create(RazorPayInterFase::class.java)
            return retroFit
        }
    }
}