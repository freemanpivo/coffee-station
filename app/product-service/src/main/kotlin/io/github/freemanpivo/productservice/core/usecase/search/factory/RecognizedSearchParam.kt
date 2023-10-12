package io.github.freemanpivo.productservice.core.usecase.search.factory

object RecognizedSearchParam {
    val INDEX: Map<String,SearchType> =
        mapOf(
            SearchType.ID.queryKey to SearchType.ID,
            SearchType.NAME.queryKey to SearchType.NAME,
            SearchType.PREPARATION.queryKey to SearchType.PREPARATION
        )
}