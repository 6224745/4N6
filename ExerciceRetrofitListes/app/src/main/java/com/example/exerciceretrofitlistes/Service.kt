package com.example.exerciceretrofitlistes

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {
    @GET("exos/long/list")
    fun listLong(): Call<List<Long>>
    @GET("exos/truc/list")
    fun listComplex(): Call<List<ObjetComplex>>
}