package io.github.freemanpivo.productservice.database.entity

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey

// The fields of this class needs to be nullable, the AWS SDK 2.00 expects a default constructor and set the fields
@DynamoDbBean
data class ProductEntity(
    @get:DynamoDbPartitionKey
    var productId: String? = "",
    @get:DynamoDbSecondaryPartitionKey(indexNames = [TableProperties.PREPARATION_INDEX])
    var preparation: String? = "",
    @get:DynamoDbSecondaryPartitionKey(indexNames = [TableProperties.NAME_INDEX])
    var name: String? = "",
    var description: String = "",
    var price: Double? = 0.0
)