package io.github.freemanpivo.productservice.api.controller

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SimpleGetControllerTest(@Autowired val restTemplate: TestRestTemplate) {

    @Test
    fun `Assert teste endpoint, content and status code`() {
        val entity = restTemplate.getForEntity<String>("/teste/123")
        val expectedOutput = "{\"data\":[{\"message_\":\"sucesso\",\"description_\":\"requisicao bem formatada com numero 123\"}]}"

        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).isEqualTo(expectedOutput)
    }
}