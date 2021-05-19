package com.dariogonmar.o2obeerfinder2.repositories

import com.dariogonmar.o2obeerfinder2.models.Beer
import com.dariogonmar.o2obeerfinder2.models.BeersResponse
import com.dariogonmar.o2obeerfinder2.services.ApiIntermediary
import retrofit2.http.Path
import javax.inject.Inject

class BeerRepository @Inject constructor(private val apiIntermediary: ApiIntermediary) {
    suspend fun getBeers(): BeersResponse = apiIntermediary.getBeers()
    suspend fun getBeer(@Path("id") id: Long): Beer? = apiIntermediary.getBeer(id)
}