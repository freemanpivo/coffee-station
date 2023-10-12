package io.github.freemanpivo.productservice.core.usecase.search.factory

import io.github.freemanpivo.productservice.core.port.ProductDatabase
import io.github.freemanpivo.productservice.core.usecase.search.strategy.ProductSearchAll
import io.github.freemanpivo.productservice.core.usecase.search.strategy.ProductSearchById
import io.github.freemanpivo.productservice.core.usecase.search.strategy.ProductSearchByName
import io.github.freemanpivo.productservice.core.usecase.search.strategy.ProductSearchByPreparation
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProductSearchFactoryConfig {

    @Bean
    fun productSearchFactory(database: ProductDatabase): ProductSearchFactory {
        val implementations = setOf(
            ProductSearchById(database),
            ProductSearchByName(database),
            ProductSearchByPreparation(database),
            ProductSearchAll(database)
        )

        return ProductSearchFactory(implementations)
    }

}