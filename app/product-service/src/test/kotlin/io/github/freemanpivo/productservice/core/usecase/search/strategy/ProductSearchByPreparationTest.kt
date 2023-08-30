package io.github.freemanpivo.productservice.core.usecase.search.strategy

import io.github.freemanpivo.productservice.core.port.ProductDatabase
import io.github.freemanpivo.productservice.core.usecase.search.TestsSamples
import io.github.freemanpivo.productservice.core.usecase.search.factory.SearchType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito

class ProductSearchByPreparationTest {
    private val database = Mockito.mock(ProductDatabase::class.java)
    private val usecase = ProductSearchByPreparation(database)
    private val queryParam = TestsSamples.queryParamPreparation()

    @Test
    fun `should return PREPARATION search type when calling searchType() method`() {
        assertEquals(SearchType.PREPARATION, usecase.searchType())
    }

    @Test
    fun `when passing an PREPARATION and the database has a reference to this PREPARATION, should return a set with items`() {
        val product = TestsSamples.productSample()
        BDDMockito.given(database.findByPreparation(queryParam.get(queryParam.searchType().queryKey)))
            .willReturn(setOf(product))

        assertEquals(setOf(product), usecase.findProducts(queryParam))
    }

    @Test
    fun `when passing an PREPARATION and the database don't have a reference to this PREPARATION, should return an empty set`() {
        BDDMockito.given(database.findByPreparation(queryParam.get(queryParam.searchType().queryKey)))
            .willReturn(setOf())

        assertTrue(usecase.findProducts(queryParam).isEmpty())
    }
}