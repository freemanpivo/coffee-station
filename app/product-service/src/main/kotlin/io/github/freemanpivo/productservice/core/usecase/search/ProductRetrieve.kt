package io.github.freemanpivo.productservice.core.usecase.search

import io.github.freemanpivo.productservice.core.domain.Product
import io.github.freemanpivo.productservice.core.port.ProductSearch
import io.github.freemanpivo.productservice.core.usecase.search.factory.ProductSearchFactory
import io.github.freemanpivo.productservice.core.usecase.search.strategy.QueryParam
import org.springframework.stereotype.Service

@Service
class ProductRetrieve(private val productSearchFactory: ProductSearchFactory): ProductSearch {

    override fun get(inputParams: Map<String, String>): Set<Product> {
        val queryParams = QueryParam(inputParams)
        val searchType = queryParams.searchType()
        val strategy = productSearchFactory.of(searchType)

        return strategy.findProducts(queryParams)
    }

}