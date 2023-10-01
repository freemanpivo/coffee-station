package io.github.freemanpivo.productservice.api.controller

import io.github.freemanpivo.productservice.api.dto.commom.SuccessPayloadQuery
import io.github.freemanpivo.productservice.core.port.ProductSearch
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
class ProductGetController(
    private val usecase: ProductSearch
) {
    private val logger = LoggerFactory.getLogger(ProductGetController::class.java)
    @GetMapping(value = ["/products"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@RequestParam queryParams: Map<String, String>): ResponseEntity<SuccessPayloadQuery> {
        logger.info("begin GET /products...")
        val products = usecase.get(queryParams)
        logger.info("end GET /products.")

        return ResponseEntity.ok(SuccessPayloadQuery(products))
    }
}