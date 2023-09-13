package io.github.freemanpivo.productservice.database.entity

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey

object DynamoProperties {
    const val TABLE_NAME = "Products"
    const val PREPARATION_INDEX = "ProductsByPreparation"
    const val NAME_INDEX = "ProductsByName"
}

@DynamoDbBean
data class ProductEntity(
    @get:DynamoDbPartitionKey
    var productId: String? = "",
    @get:DynamoDbSecondaryPartitionKey(indexNames = [DynamoProperties.PREPARATION_INDEX])
    var preparation: String? = "",
    @get:DynamoDbSecondaryPartitionKey(indexNames = [DynamoProperties.NAME_INDEX])
    var name: String? = "",
    var description: String = "",
    var price: Double? = 0.0
)