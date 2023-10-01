package io.github.freemanpivo.productservice.database.operation

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
    private val logger = LoggerFactory.getLogger(ProductInDynamo::class.java)
    override fun findAll(): Set<Product> {
        logger.info("querying all products...")
        val entities = repository.scanProducts()
        logger.info("queried all products. Total of {} items", entities.size)

        return entities.stream()
            .map { entity -> mapper.toModel(entity) }
            .collect(Collectors.toSet())
    }

    override fun findById(id: String): Optional<Product> {
        logger.info("querying products by id...")
        val productEntity = repository.queryTableByPrimaryKey(id).getOrNull() ?: return Optional.empty()
        val product = mapper.toModel(productEntity)
        logger.info("end of query by id.")

        return Optional.of(product)
    }

    override fun findByName(name: String): Set<Product> {
        logger.info("querying products by name...")
        val entities = repository.queryIndexName(name)
        logger.info("queried products by name. Total of {} items", entities.size)

        return entities.stream()
            .map { entity -> mapper.toModel(entity) }
            .collect(Collectors.toSet())
    }

    override fun findByPreparation(preparation: String): Set<Product> {
        logger.info("querying products by preparation...")
        val entities = repository.queryIndexPreparation(preparation)
        logger.info("queried products by preparation. Total of {} items", entities.size)


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