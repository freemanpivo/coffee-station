package io.github.freemanpivo.productservice.core.port

import io.github.freemanpivo.productservice.core.domain.Product

interface ProductSearch {
    fun get(inputParams: Map<String, String>): Set<Product>
}