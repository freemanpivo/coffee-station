package io.github.freemanpivo.productservice.core.usecase.search.strategy

import io.github.freemanpivo.productservice.core.domain.Product
import io.github.freemanpivo.productservice.core.port.ProductDatabase
import io.github.freemanpivo.productservice.core.usecase.search.factory.SearchType
import org.springframework.stereotype.Component

@Component
class ProductSearchByName(private val database: ProductDatabase): SearchStrategy {
    override fun searchType(): SearchType {
        return SearchType.NAME
    }

    override fun findProducts(queryParams: QueryParam): Set<Product> {

        return database.findByName(queryParams.get(queryParams.searchType().queryKey))
    }
}