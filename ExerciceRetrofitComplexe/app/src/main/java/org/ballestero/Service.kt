package org.ballestero

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    @GET("exos/truc/complexe")
    fun listReposString(@Query("name") utilisateur: String): Call<String>
}