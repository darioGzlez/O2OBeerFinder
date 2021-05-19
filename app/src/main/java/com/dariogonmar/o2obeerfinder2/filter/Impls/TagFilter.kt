package com.dariogonmar.o2obeerfinder2.filter.Impls

import com.dariogonmar.o2obeerfinder2.filter.IFilter
import com.dariogonmar.o2obeerfinder2.models.Beer

class TagFilter: IFilter {
    override fun applyFilter(query: String, beers: List<Beer>): List<Beer> {
        return beers.filter { beer -> beer.tagline.uppercase().contains(query) }
    }
}