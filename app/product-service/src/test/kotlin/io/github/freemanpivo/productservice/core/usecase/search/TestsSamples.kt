package io.github.freemanpivo.productservice.core.usecase.search

import io.github.freemanpivo.productservice.core.domain.Product
import io.github.freemanpivo.productservice.core.usecase.search.strategy.QueryParam

object TestsSamples {
    const val id = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
    const val name = "Espresso"
    const val preparation = "CAFE"
    const val description = "desc"
    const val price = 5.00

    fun queryParamId(): QueryParam{
        return QueryParam(mapOf("id" to id))
    }

    fun queryParamName(): QueryParam{
        return QueryParam(mapOf("name" to name))
    }

    fun queryParamPreparation(): QueryParam{
        return QueryParam(mapOf("preparation" to preparation))
    }

    fun productSample(): Product {
        return Product(id, preparation, name, description, price)
    }
}