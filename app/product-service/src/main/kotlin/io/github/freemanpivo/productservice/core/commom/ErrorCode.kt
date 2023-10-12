package io.github.freemanpivo.productservice.core.commom

enum class ErrorCode(val code: String, val message: String) {
    VALIDATION_ERROR("001", "fields has invalid data"),
    INVALID_INPUT_PARAM("002", "invalid query params")
}