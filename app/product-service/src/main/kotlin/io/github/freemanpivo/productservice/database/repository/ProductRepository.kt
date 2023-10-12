package io.github.freemanpivo.productservice.database.repository

import io.github.freemanpivo.productservice.database.entity.ProductEntity

interface ProductRepository {
    fun queryTableByPrimaryKey(primaryKey: String): Result<ProductEntity>
    fun queryIndexName(name: String): Set<ProductEntity>
    fun queryIndexPreparation(preparation: String): Set<ProductEntity>
    fun scanProducts(): Set<ProductEntity>
}