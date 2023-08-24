package io.github.freemanpivo.productservice.api.controller

import io.github.freemanpivo.productservice.api.dto.TesteDto
import io.github.freemanpivo.productservice.api.dto.commom.SuccessPayloadQuery
import io.github.freemanpivo.productservice.core.domain.Product
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleGetController {

    @GetMapping(value = ["/teste/{teste_id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@PathVariable("teste_id") testeId: String): ResponseEntity<SuccessPayloadQuery> {
        try {
            val parseLong = Integer.parseInt(testeId)
            val response = TesteDto("sucesso", "requisicao bem formatada com numero ${parseLong}")
            val p = Product("a","a","a","a","1.00")

            return ResponseEntity.ok(SuccessPayloadQuery(setOf(response)))
        } catch (exception: Exception) {
            throw exception
        }
    }
}