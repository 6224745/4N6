package org.ballestero

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service2 {
    @GET("exos/long/list")
    fun listReposLong(): Call<Long>
}