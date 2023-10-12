package io.github.freemanpivo.productservice.core.usecase.search.strategy

import io.github.freemanpivo.productservice.core.domain.Product
import io.github.freemanpivo.productservice.core.port.ProductDatabase
import io.github.freemanpivo.productservice.core.usecase.search.factory.SearchType
import org.springframework.stereotype.Component

@Component
class ProductSearchByPreparation(private val database: ProductDatabase): SearchStrategy {
    override fun searchType(): SearchType {
        return SearchType.PREPARATION
    }

    override fun findProducts(queryParams: QueryParam): Set<Product> {
        return database.findByPreparation(queryParams.get(queryParams.searchType().queryKey))
    }
}