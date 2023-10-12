package io.github.freemanpivo.productservice.core.usecase.search.factory

import io.github.freemanpivo.productservice.core.exception.DomainValidationException
import io.github.freemanpivo.productservice.core.usecase.search.strategy.SearchStrategy
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProductSearchFactory(private val implementations: Set<SearchStrategy>) {
    private val strategies: MutableMap<SearchType, SearchStrategy>

    init {
        val tempStrategies = HashMap<SearchType, SearchStrategy>()
        implementations.forEach { tempStrategies[it.searchType()] = it }
        strategies = Collections.unmodifiableMap(tempStrategies)
    }

    fun of(searchType: SearchType): SearchStrategy {
        return try {
            strategies[searchType]!!
        } catch (exception: Exception) {
            throw DomainValidationException("", "", listOf())
        }
    }
}