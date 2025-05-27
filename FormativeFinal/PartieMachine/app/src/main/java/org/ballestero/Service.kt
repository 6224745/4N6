package org.ballestero

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {
    @GET("exam/h25/{x}")
    fun envoiString(@Path("x") x: String): Call<String>
}