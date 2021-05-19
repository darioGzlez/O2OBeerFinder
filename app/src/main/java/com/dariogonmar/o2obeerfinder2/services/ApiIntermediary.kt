package com.dariogonmar.o2obeerfinder2.services

import com.dariogonmar.o2obeerfinder2.models.Beer
import com.dariogonmar.o2obeerfinder2.models.BeersResponse
import retrofit2.http.Path

interface ApiIntermediary {
    suspend fun getBeers(): BeersResponse
    suspend fun getBeer(@Path("id") id: Long): Beer?
}