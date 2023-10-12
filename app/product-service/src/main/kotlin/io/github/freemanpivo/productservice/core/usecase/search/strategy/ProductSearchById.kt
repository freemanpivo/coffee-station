package io.github.freemanpivo.productservice.core.usecase.search.strategy

import io.github.freemanpivo.productservice.core.domain.Product
import io.github.freemanpivo.productservice.core.port.ProductDatabase
import io.github.freemanpivo.productservice.core.usecase.search.factory.SearchType
import org.springframework.stereotype.Component

@Component
class ProductSearchById(private val database: ProductDatabase): SearchStrategy {
    override fun searchType(): SearchType {
        return SearchType.ID
    }

    override fun findProducts(queryParams: QueryParam): Set<Product> {
        val product = database.findById(queryParams.get(queryParams.searchType().queryKey))
        if (product.isEmpty) return setOf()

        return setOf(product.get())
    }
}