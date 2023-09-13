package io.github.freemanpivo.productservice.database.repository

import io.github.freemanpivo.productservice.database.entity.DynamoProperties
import io.github.freemanpivo.productservice.database.entity.ProductEntity
import org.springframework.stereotype.Component
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest

@Component
class DynamoDbOperation(private val client: DynamoDbEnhancedAsyncClient): ProductRepository {
    private val productEntity = TableSchema.fromBean(ProductEntity::class.java)
    private val principalTable = client.table(DynamoProperties.TABLE_NAME, productEntity)

    override fun queryTableByPrimaryKey(primaryKey: String): Result<ProductEntity> {
        val result = principalTable.getItem(Key.builder().partitionValue(primaryKey).build())
        return try {
            Result.success(result.get())
        } catch (exception: Exception) {
            // TODO: AJUSTAR
            Result.failure(Exception("TODO"))
        }
    }

    override fun queryIndexName(name: String): Set<ProductEntity> {
        val index = principalTable.index(DynamoProperties.NAME_INDEX)
        val key = Key.builder().partitionValue(name).build()
        val q = QueryConditional.keyEqualTo(key)
        val products = mutableSetOf<ProductEntity>()

        index.query(QueryEnhancedRequest.builder().queryConditional(q).build()).subscribe {
            page -> page.items().forEach { item -> products.add(item) }
        }.get()

        return products
    }

    override fun queryIndexPreparation(preparation: String): Set<ProductEntity> {
        val index = principalTable.index(DynamoProperties.PREPARATION_INDEX)
        val key = Key.builder().partitionValue(preparation).build()
        val q = QueryConditional.keyEqualTo(key)
        val products = mutableSetOf<ProductEntity>()

        index.query(QueryEnhancedRequest.builder().queryConditional(q).build()).subscribe {
            page -> page.items().forEach { item -> products.add(item) }
        }.get()

        return products
    }
}