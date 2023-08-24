package io.github.freemanpivo.productservice.core.usecase.search.factory

enum class SearchType(val queryKey: String) {
    ID("id"), NAME("name"), PREPARATION("preparation");

    companion object {
        fun byQueryKey(queryKey: String): SearchType? {
            return RecognizedSearchParam.INDEX[queryKey]
        }
    }
}



