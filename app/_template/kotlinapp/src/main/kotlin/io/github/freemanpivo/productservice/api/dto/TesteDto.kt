package io.github.freemanpivo.productservice.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TesteDto(@JsonProperty("message_") val message: String,
                    @JsonProperty("description_") val description: String)
