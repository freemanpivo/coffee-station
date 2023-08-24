package io.github.freemanpivo.productservice.database.operation

import io.github.freemanpivo.productservice.core.domain.Product
import io.github.freemanpivo.productservice.core.port.ProductDatabase
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProductInDynamo : ProductDatabase{
    override fun findById(id: String): Optional<Product> {
        val products = products().firstOrNull { it.id == id }
        return if (products == null) Optional.empty() else Optional.of(products)
    }

    override fun findByName(name: String): Set<Product> {
        val products = products().firstOrNull { it.name == name }
        return if (products == null) setOf() else setOf(products)
    }

    override fun findByPreparation(preparation: String): Set<Product> {
        val products = products().firstOrNull { it.preparation == preparation }
        return if (products == null) setOf() else setOf(products)
    }

    override fun create(product: Product): Product {
        TODO("Not yet implemented")
    }

    override fun update(product: Product): Product {
        TODO("Not yet implemented")
    }

    private fun products(): Set<Product> {
        return setOf(
            Product(UUID.randomUUID().toString(), "CAFE", "Espresso", "bravissimo", "5.00"),
            Product(UUID.randomUUID().toString(), "CAFE", "Cappuccino", "esplendido", "7.00")
        )
    }
}