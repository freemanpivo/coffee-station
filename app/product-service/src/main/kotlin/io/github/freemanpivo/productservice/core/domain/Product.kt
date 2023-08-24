package io.github.freemanpivo.productservice.core.domain

import io.github.freemanpivo.productservice.core.commom.ErrorCode
import io.github.freemanpivo.productservice.core.commom.RegexPattern
import io.github.freemanpivo.productservice.core.commom.ValidationMessage
import io.github.freemanpivo.productservice.core.exception.DomainValidationException

data class Product(
    val id: String,
    val preparation: String,
    val name: String,
    val description: String,
    val price: String
) {
    private val validationIssues: MutableList<String> = mutableListOf()

    init {
        if (id.isBlank() || !id.matches(Regex(RegexPattern.UUID_REGEX)))
            validationIssues.add(ValidationMessage.VALIDATION_ISSUE_ID)
        if (preparation.isBlank()) validationIssues.add(ValidationMessage.VALIDATION_ISSUE_PREPARATION)
        if (name.isBlank()) validationIssues.add(ValidationMessage.VALIDATION_ISSUE_NAME)
        if (description.isBlank()) validationIssues.add(ValidationMessage.VALIDATION_ISSUE_DESCRIPTION)
        if (price.isBlank()) validationIssues.add(ValidationMessage.VALIDATION_ISSUE_PRICE)

        if (validationIssues.isNotEmpty()) {
            throw DomainValidationException(
                ErrorCode.VALIDATION_ERROR.code,
                ErrorCode.VALIDATION_ERROR.message,
                validationIssues.toList())
        }
    }
}
