package io.github.freemanpivo.productservice.core.commom

object ValidationMessage {
    const val VALIDATION_ISSUE_ID = "id must be an UUID hash"
    const val VALIDATION_ISSUE_PREPARATION = "preparation must be CAFE or KITCHEN"
    const val VALIDATION_ISSUE_NAME = "name must be filled"
    const val VALIDATION_ISSUE_DESCRIPTION = "name must be filled"
    const val VALIDATION_ISSUE_PRICE = "price must be in format 0.00"
}