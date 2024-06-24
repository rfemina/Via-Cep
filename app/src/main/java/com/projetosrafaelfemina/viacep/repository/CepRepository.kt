package com.projetosrafaelfemina.viacep.repository

import com.projetosrafaelfemina.viacep.api.Api
import com.projetosrafaelfemina.viacep.model.Address
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CepRepository {

    private val api: Api

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://viacep.com.br/ws/") // Vir do base url gradle
            .build()

        api = retrofit.create(Api::class.java)
    }

    fun getAddress(cep: String, callback: (Result<Address>) -> Unit) {
        val call = api.getAddress(cep)
        call.enqueue(object : Callback<Address> {
            override fun onResponse(call: Call<Address>, response: retrofit2.Response<Address>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback(Result.success(it))
                    } ?: callback(Result.failure(Throwable("Resposta sem corpo")))
                } else {
                    callback(Result.failure(Throwable("Cep inválido")))
                }
            }

            override fun onFailure(call: Call<Address>, t: Throwable) {
                callback(Result.failure(t))
            }
        })
    }
}
