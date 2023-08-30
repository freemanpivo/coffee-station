package io.github.freemanpivo.productservice.api.exception

import io.github.freemanpivo.productservice.core.exception.DomainValidationException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail

class GlobalExceptionHandlerTest {
    private val handler = GlobalExceptionHandler()

    @Test
    fun `should handle generic exception returning a problem detail`() {
        val genericException = Exception("")
        val expectedProblemDetail = ProblemDetail
            .forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, genericException.localizedMessage)

        val actual = handler.handleException(genericException)

        assertEquals(expectedProblemDetail.status, actual.status)
    }

    @Test
    fun `should handle DomainValidationException returning a problem detail`() {
        val domainException = DomainValidationException("error", "error", listOf())
        val expectedProblemDetail = ProblemDetail
            .forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, domainException.localizedMessage)

        val actual = handler.handleValidationException(domainException)

        assertEquals(expectedProblemDetail.status, actual.status)
        assertEquals(domainException.message, actual.title)
        assertEquals(domainException.code, actual.properties!!["code"])
        assertTrue(actual.properties!!.isNotEmpty())
    }
}