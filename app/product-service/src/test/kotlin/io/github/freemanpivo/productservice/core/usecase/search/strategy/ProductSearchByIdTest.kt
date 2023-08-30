package io.github.freemanpivo.productservice.core.usecase.search.strategy

import io.github.freemanpivo.productservice.core.port.ProductDatabase
import io.github.freemanpivo.productservice.core.usecase.search.TestsSamples
import io.github.freemanpivo.productservice.core.usecase.search.factory.SearchType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import java.util.*

class ProductSearchByIdTest {
    private val database = Mockito.mock(ProductDatabase::class.java)
    private val usecase = ProductSearchById(database)
    private val queryParam = TestsSamples.queryParamId()

    @Test
    fun `should return ID search type when calling searchType() method`() {
        assertEquals(SearchType.ID, usecase.searchType())
    }

    @Test
    fun `when passing an ID and the database has a reference to this ID, should return a set with one item`() {
        val product = TestsSamples.productSample()
        given(database.findById(queryParam.get(SearchType.ID.queryKey)))
            .willReturn(Optional.of(product))

        assertEquals(setOf(product), usecase.findProducts(queryParam))
    }

    @Test
    fun `when passing an ID and the database don't have a reference to this ID, should return an empty set`() {
        given(database.findById(queryParam.get(SearchType.ID.queryKey)))
            .willReturn(Optional.empty())

        assertTrue(usecase.findProducts(queryParam).isEmpty())
    }
}