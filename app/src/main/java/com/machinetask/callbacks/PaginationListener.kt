package com.machinetask.callbacks

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

 class PaginationListener(private val pageSize: Int = 10,private val callback:PaginationEvents) :
    RecyclerView.OnScrollListener() {

    private var layoutManager: LinearLayoutManager? = null
    fun setLayoutManager(manager: LinearLayoutManager) {
        layoutManager = manager;
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        layoutManager ?: return
        val visibleItemCount = layoutManager!!.childCount
        val totalItemCount = layoutManager!!.itemCount
        val firstVisibleItemPosition = layoutManager!!.findFirstVisibleItemPosition()
        if (!callback.isLoading && !callback.isLastPage) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= pageSize) {
                callback.loadMoreItems()
            }
        }
    }

}