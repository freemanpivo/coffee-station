package io.github.freemanpivo.productservice.core.usecase.search.strategy

import io.github.freemanpivo.productservice.core.domain.Product
import io.github.freemanpivo.productservice.core.usecase.search.factory.SearchType

interface SearchStrategy {
    fun searchType(): SearchType
    fun findProducts(queryParams: QueryParam): Set<Product>
}