package com.machinetask.callbacks

interface PaginationEvents {
    fun loadMoreItems()
    val isLastPage: Boolean
    val isLoading: Boolean
}