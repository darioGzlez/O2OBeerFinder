package com.dariogonmar.o2obeerfinder2.services

import com.dariogonmar.o2obeerfinder2.models.Beer
import com.dariogonmar.o2obeerfinder2.models.BeersResponse
import javax.inject.Inject

class ApiIntermediaryImpl @Inject constructor(private val apiService: Api): ApiIntermediary {

    override suspend fun getBeers(): BeersResponse {
        return apiService.getBeers()
    }

    override suspend fun getBeer(id: Long): Beer? {
        return apiService.getBeer(id).getOrNull(0)
    }
}