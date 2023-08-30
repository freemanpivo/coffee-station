package io.github.freemanpivo.productservice.core.usecase.search.strategy

import io.github.freemanpivo.productservice.core.port.ProductDatabase
import io.github.freemanpivo.productservice.core.usecase.search.TestsSamples
import io.github.freemanpivo.productservice.core.usecase.search.factory.SearchType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito

class ProductSearchByNameTest {
    private val database = Mockito.mock(ProductDatabase::class.java)
    private val usecase = ProductSearchByName(database)
    private val queryParam = TestsSamples.queryParamName()

    @Test
    fun `should return NAME search type when calling searchType() method`() {
        assertEquals(SearchType.NAME, usecase.searchType())
    }

    @Test
    fun `when passing an NAME and the database has a reference to this NAME, should return a set with items`() {
        val product = TestsSamples.productSample()
        BDDMockito.given(database.findByName(queryParam.get(SearchType.NAME.queryKey)))
            .willReturn(setOf(product))

        assertEquals(setOf(product), usecase.findProducts(queryParam))
    }

    @Test
    fun `when passing an NAME and the database don't have a reference to this NAME, should return an empty set`() {
        BDDMockito.given(database.findByName(queryParam.get(SearchType.NAME.queryKey)))
            .willReturn(setOf())

        assertTrue(usecase.findProducts(queryParam).isEmpty())
    }
}