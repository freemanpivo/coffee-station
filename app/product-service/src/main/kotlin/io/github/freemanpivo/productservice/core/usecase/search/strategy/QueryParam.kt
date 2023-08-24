package io.github.freemanpivo.productservice.core.usecase.search.strategy

import io.github.freemanpivo.productservice.core.commom.ErrorCode
import io.github.freemanpivo.productservice.core.commom.RegexPattern
import io.github.freemanpivo.productservice.core.exception.DomainValidationException
import io.github.freemanpivo.productservice.core.usecase.search.factory.RecognizedSearchParam
import io.github.freemanpivo.productservice.core.usecase.search.factory.SearchType
import java.lang.IllegalArgumentException
import java.util.Collections
import java.util.regex.Pattern

class QueryParam(inputParams: Map<String,String>) {
    private val params: Map<String, String>

    init {
        if (!hasValidInputParams(inputParams))
            throw DomainValidationException(ErrorCode.INVALID_INPUT_PARAM.code, ErrorCode.INVALID_INPUT_PARAM.message, listOf())
        params = Collections.unmodifiableMap(inputParams)
    }

    fun get(key: String): String {
        return params[key]!!
    }

    fun searchType(): SearchType {
        try {
            val searchKey = params.keys.stream().findFirst().get()

            return SearchType.byQueryKey(searchKey) ?: throw IllegalArgumentException("")
        } catch (exception: Exception) {
            throw exception
        }
    }

    private fun hasValidInputParams(inputParams: Map<String, String>): Boolean {
        if (inputParams.keys.isEmpty()) return false
        if (!hasValidKeySet(inputParams.keys)) return false
        val key = inputParams.keys.stream().findFirst().get()
        val value = inputParams[key] ?: return false

        return when(key) {
            "id" -> Pattern.matches(RegexPattern.UUID_REGEX, value)
            "name" -> value.isNotBlank()
            "preparation" -> value.isNotBlank()
            else -> false
        }
    }

    private fun hasValidKeySet(keys: Set<String>): Boolean {
        if (keys.size != 1) return false

        return RecognizedSearchParam.INDEX.containsKey(keys.stream().findFirst().get())
    }
}