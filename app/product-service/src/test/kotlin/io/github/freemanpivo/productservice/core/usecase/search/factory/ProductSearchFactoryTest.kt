package io.github.freemanpivo.productservice.core.usecase.search.factory

import io.github.freemanpivo.productservice.core.port.ProductDatabase
import io.github.freemanpivo.productservice.core.usecase.search.strategy.ProductSearchAll
import io.github.freemanpivo.productservice.core.usecase.search.strategy.ProductSearchById
import io.github.freemanpivo.productservice.core.usecase.search.strategy.ProductSearchByName
import io.github.freemanpivo.productservice.core.usecase.search.strategy.ProductSearchByPreparation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ProductSearchFactoryTest {
    private val database = Mockito.mock(ProductDatabase::class.java)
    private val usecaseAllProducts = ProductSearchAll(database)
    private val usecaseById = ProductSearchById(database)
    private val usecaseByName = ProductSearchByName(database)
    private val usecaseByPreparation = ProductSearchByPreparation(database)
    private val implementations = setOf(
        usecaseByName,
        usecaseById,
        usecaseByPreparation,
        usecaseAllProducts
    )
    private val factory = ProductSearchFactory(implementations)

    @Test
    fun `should return search by id implementation`() {
        assertEquals(usecaseById, factory.of(SearchType.ID))
    }

    @Test
    fun `should return search by name implementation`() {
        assertEquals(usecaseByName, factory.of(SearchType.NAME))
    }

    @Test
    fun `should return search by preparation implementation`() {
        assertEquals(usecaseByPreparation, factory.of(SearchType.PREPARATION))
    }

    @Test
    fun `should return search all implementation`() {
        assertEquals(usecaseAllProducts, factory.of(SearchType.ALL))
    }

}