package org.ballestero

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {
    @GET("exos/truc/list")
    fun listReposString(): Call<String>
}