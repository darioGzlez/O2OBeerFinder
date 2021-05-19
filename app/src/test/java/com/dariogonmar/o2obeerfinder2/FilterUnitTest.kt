package com.dariogonmar.o2obeerfinder2

import com.dariogonmar.o2obeerfinder2.filter.BeerFilter
import com.dariogonmar.o2obeerfinder2.filter.Impls.DescripcionFilter
import com.dariogonmar.o2obeerfinder2.filter.Impls.NameFilter
import com.dariogonmar.o2obeerfinder2.filter.Impls.TagFilter
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FilterUnitTest {
    private lateinit var repostory: MockRepository

    private lateinit var beerFilter: BeerFilter
    private lateinit var nameFilter: NameFilter
    private lateinit var descriptionFilter: DescripcionFilter
    private lateinit var tagFilter: TagFilter

    @Before
    fun before() {
        repostory = MockRepository()
        nameFilter = NameFilter()
        descriptionFilter = DescripcionFilter()
        tagFilter = TagFilter()
        beerFilter = BeerFilter(listOf(nameFilter, descriptionFilter, tagFilter))
    }

    @Test
    fun testNameFilter() = runBlocking {
        val beers = repostory.getBeers()
        assertEquals(beers.size, 3)
        assertEquals(nameFilter.applyFilter("A", beers).size, 1)
        assertEquals(nameFilter.applyFilter("B", beers).size, 1)
        assertEquals(nameFilter.applyFilter("C", beers).size, 1)

        assertEquals(nameFilter.applyFilter("D", beers).size, 2)

        assertEquals(nameFilter.applyFilter("G", beers).size, 3)
    }

    @Test
    fun testDescriptionFilter() = runBlocking {
        val beers = repostory.getBeers()
        assertEquals(beers.size, 3)
        assertEquals(descriptionFilter.applyFilter("1", beers).size, 1)
        assertEquals(descriptionFilter.applyFilter("2", beers).size, 1)
        assertEquals(descriptionFilter.applyFilter("3", beers).size, 1)

        assertEquals(descriptionFilter.applyFilter("4", beers).size, 2)

        assertEquals(descriptionFilter.applyFilter("5", beers).size, 3)
    }

    @Test
    fun testTagFilter() = runBlocking {
        val beers = repostory.getBeers()
        assertEquals(beers.size, 3)
        assertEquals(tagFilter.applyFilter("M", beers).size, 1)
        assertEquals(tagFilter.applyFilter("P", beers).size, 1)
        assertEquals(tagFilter.applyFilter("p", beers).size, 0)
        assertEquals(tagFilter.applyFilter("N", beers).size, 1)
        assertEquals(tagFilter.applyFilter("X", beers).size, 0)
    }

    @Test
    fun testCombineFilter() = runBlocking {
        val beers = repostory.getBeers()
        assertEquals(beerFilter.applyFilter("5", beers).size, 3)
        assertEquals(beerFilter.applyFilter("P", beers).size, 1)
        assertEquals(beerFilter.applyFilter("CFG", beers).size, 1)
        assertEquals(beerFilter.applyFilter("WWW", beers).size, 0)
    }

}