package io.github.freemanpivo.productservice.core.port

import io.github.freemanpivo.productservice.core.domain.Product
import java.util.Optional

interface ProductDatabase {
    fun findById(id: String): Optional<Product>
    fun findByName(name: String): Set<Product>
    fun findByPreparation(preparation: String): Set<Product>
    fun create(product: Product): Product
    fun update(product: Product): Product
}