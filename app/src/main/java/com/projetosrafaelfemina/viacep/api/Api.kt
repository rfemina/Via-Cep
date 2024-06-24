package com.projetosrafaelfemina.viacep.api

import com.projetosrafaelfemina.viacep.model.Address
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("ws/{cep}/json/")
    fun getAddress(@Path("cep") cep: String): Call<Address>
}