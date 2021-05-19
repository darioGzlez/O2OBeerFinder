package com.dariogonmar.o2obeerfinder2.services

import com.dariogonmar.o2obeerfinder2.models.BeersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("beers")
    suspend fun getBeers(): BeersResponse

    @GET("beers/{id}")
    suspend fun getBeer(@Path("id") id: Long): BeersResponse

}