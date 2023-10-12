package io.github.freemanpivo.productservice.database.entity

import io.github.freemanpivo.productservice.core.domain.Product
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ProductEntityMapper {
    @Mapping(source = "productId", target = "id")
    fun toModel(entity: ProductEntity): Product
    @Mapping(source = "id", target = "productId")
    fun toEntity(model: Product): ProductEntity
}
