package com.dariogonmar.o2obeerfinder2.filter

import com.dariogonmar.o2obeerfinder2.models.Beer

interface IFilter {
    fun applyFilter(query: String, beers: List<Beer>): List<Beer>
}