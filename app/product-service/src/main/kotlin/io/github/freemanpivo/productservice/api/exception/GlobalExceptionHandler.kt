package io.github.freemanpivo.productservice.api.exception

import io.github.freemanpivo.productservice.core.exception.DomainValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.Instant

@RestControllerAdvice
class GlobalExceptionHandler: ResponseEntityExceptionHandler() {

    /*
    * Using RFC 7807 - https://www.rfc-editor.org/rfc/rfc7807.html
    * */

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.localizedMessage)
        problemDetail.title = "General Exception "
        problemDetail.detail = "This is a captured dummy exception"
        problemDetail.setProperty("category", "dummy error")
        problemDetail.setProperty("timestamp", Instant.now())
        problemDetail.setProperty("stacktrace", exception.stackTrace)

        return problemDetail
    }

    @ExceptionHandler(DomainValidationException::class)
    fun handleValidationException(exception: DomainValidationException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.localizedMessage)
        problemDetail.title = exception.message
        problemDetail.detail = "Cant build Product"
        problemDetail.setProperty("code", exception.code)
        problemDetail.setProperty("fields", exception.fields)

        return problemDetail
    }
}