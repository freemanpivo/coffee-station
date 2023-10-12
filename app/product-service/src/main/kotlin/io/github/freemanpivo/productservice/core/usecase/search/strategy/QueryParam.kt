package io.github.freemanpivo.productservice.core.usecase.search.strategy

import io.github.freemanpivo.productservice.core.commom.ErrorCode
import io.github.freemanpivo.productservice.core.exception.DomainValidationException
import io.github.freemanpivo.productservice.core.usecase.search.factory.RecognizedSearchParam
import io.github.freemanpivo.productservice.core.usecase.search.factory.SearchType
import java.util.*

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
            if (params.isEmpty()) return SearchType.ALL
            val searchKey = params.keys.stream().findFirst().get()

            return SearchType.byQueryKey(searchKey) ?: throw IllegalArgumentException("")
        } catch (exception: Exception) {
            throw exception
        }
    }

    private fun hasValidInputParams(inputParams: Map<String, String>): Boolean {
        if (inputParams.keys.isEmpty()) return true
        if (!hasValidKeySet(inputParams.keys)) return false
        val key = inputParams.keys.stream().findFirst().get()
        val value = inputParams[key] ?: return false

        return when(key) {
            "id" -> value.isNotBlank() && value.length < MAXIMUM_ID_LENGTH
            "name" -> value.isNotBlank() && value.length < MAXIMUM_NAME_LENGTH
            "preparation" -> value.isNotBlank() && value.length < MAXIMUM_PREPARATION_LENGTH
            else -> false
        }
    }

    private fun hasValidKeySet(keys: Set<String>): Boolean {
        if (keys.size != 1) return false

        return RecognizedSearchParam.INDEX.containsKey(keys.stream().findFirst().get())
    }

    companion object {
        const val MAXIMUM_ID_LENGTH = 40
        const val MAXIMUM_NAME_LENGTH = 200
        const val MAXIMUM_PREPARATION_LENGTH = 10
    }
}