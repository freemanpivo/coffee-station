package io.github.freemanpivo.productservice.api.controller

import io.github.freemanpivo.productservice.api.dto.TesteDto
import io.github.freemanpivo.productservice.api.dto.commom.SuccessPayloadQuery
import io.github.freemanpivo.productservice.core.domain.Product
import io.github.freemanpivo.productservice.core.port.ProductSearch
import io.github.freemanpivo.productservice.core.usecase.search.factory.SearchType
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductGetController(private val usecase: ProductSearch) {
    private val log = LoggerFactory.getLogger(ProductGetController::class.java)

    @GetMapping(value = ["/products"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@RequestParam queryParams: Map<String, String>): ResponseEntity<SuccessPayloadQuery> {
        log.debug("begin GET /products...")
        val products = usecase.get(queryParams)
        log.debug("end GET /products.")

        return ResponseEntity.ok(SuccessPayloadQuery(products))
    }
}