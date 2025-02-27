package com.example.intrarezo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {
    @GET("exam/representations/{nombre}")
    fun listReposString(@Path("nombre") nombre: String): Call<List<Repre>>
}