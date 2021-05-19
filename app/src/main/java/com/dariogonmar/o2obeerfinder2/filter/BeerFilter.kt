package com.dariogonmar.o2obeerfinder2.filter

import com.dariogonmar.o2obeerfinder2.filter.Impls.DescripcionFilter
import com.dariogonmar.o2obeerfinder2.filter.Impls.NameFilter
import com.dariogonmar.o2obeerfinder2.filter.Impls.TagFilter
import com.dariogonmar.o2obeerfinder2.models.Beer

class BeerFilter(val filters: List<IFilter>): IFilter {
    override fun applyFilter(query: String, beers: List<Beer>): List<Beer> {
        return filters.map {  iFilter ->
            iFilter.applyFilter(query, beers)
        }.flatten().distinctBy { it.id }
    }
}