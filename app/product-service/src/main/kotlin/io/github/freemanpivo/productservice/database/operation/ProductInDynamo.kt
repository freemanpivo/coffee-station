package io.github.freemanpivo.productservice.database.operation

import io.github.freemanpivo.productservice.api.controller.ProductGetController
import io.github.freemanpivo.productservice.core.domain.Product
import io.github.freemanpivo.productservice.core.port.ProductDatabase
import io.github.freemanpivo.productservice.database.entity.ProductEntityMapper
import io.github.freemanpivo.productservice.database.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class ProductInDynamo(
    private val repository: ProductRepository,
    private val mapper: ProductEntityMapper
) : ProductDatabase {
    private val log = LoggerFactory.getLogger(ProductGetController::class.java)
    override fun findById(id: String): Optional<Product> {
        val productEntity = repository.queryTableByPrimaryKey(id).getOrNull() ?: return Optional.empty()
        val product = mapper.toModel(productEntity)

        return Optional.of(product)
    }

    override fun findByName(name: String): Set<Product> {
        val entities = repository.queryIndexName(name)

        return entities.stream()
            .map { entity -> mapper.toModel(entity) }
            .collect(Collectors.toSet())
    }

    override fun findByPreparation(preparation: String): Set<Product> {
        val entities = repository.queryIndexPreparation(preparation)

        return entities.stream()
            .map { entity -> mapper.toModel(entity) }
            .collect(Collectors.toSet())
    }

    override fun create(product: Product): Product {
        TODO("Not yet implemented")
    }

    override fun update(product: Product): Product {
        TODO("Not yet implemented")
    }
}