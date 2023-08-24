package io.github.freemanpivo.productservice.core.exception

open class BaseException(val code: String, override val message: String, val fields: List<String>): RuntimeException(){}