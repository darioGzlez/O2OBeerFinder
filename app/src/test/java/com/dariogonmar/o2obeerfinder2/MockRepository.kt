package com.dariogonmar.o2obeerfinder2

import com.dariogonmar.o2obeerfinder2.models.Beer
import com.dariogonmar.o2obeerfinder2.models.BeersResponse
import com.dariogonmar.o2obeerfinder2.services.ApiIntermediary

class MockRepository: ApiIntermediary {

    private val items = listOf(Beer(1, "ADG", "145", "P"), Beer(2, "BDG5", "245", "M"), Beer(3, "CFG", "35", "N"))

    override suspend fun getBeers(): BeersResponse {
        return BeersResponse(items)
    }

    override suspend fun getBeer(id: Long): Beer? {
        return items.first { it.id == id }
    }
}
