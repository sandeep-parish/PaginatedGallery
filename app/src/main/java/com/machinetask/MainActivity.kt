package com.machinetask

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.machinetask.adapter.PlacesListAdapter
import com.machinetask.callbacks.PaginationEvents
import com.machinetask.callbacks.PaginationListener
import com.machinetask.databinding.ActivityMainBinding
import com.machinetask.viewmodal.MainViewModal
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.shimmer_grid_placeholder.*

class MainActivity : AppCompatActivity(), PaginationEvents {

    private val ITEM_PER_PAGE = 30;
    private var isLast: Boolean = false
    private var disposable: Disposable? = null
    private val adapter by lazy { PlacesListAdapter() }
    private val paginationListener by lazy { PaginationListener(ITEM_PER_PAGE, this) }
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModal: MainViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModal =
            ViewModelProvider.AndroidViewModelFactory(application).create(MainViewModal::class.java)

        binding.mainViewModal = viewModal

        rvPlacesList.adapter = adapter
        paginationListener.setLayoutManager(rvPlacesList.layoutManager as LinearLayoutManager)
        rvPlacesList.addOnScrollListener(paginationListener)

        setViewModal()
    }

    private fun setViewModal() {
        viewModal.placesListEvent.observe(this, {
            isLast = (it?.size ?: 0) < ITEM_PER_PAGE
            adapter.setList(it, viewModal.pageNo.value == 1L)
        })

        viewModal.pageNo.observe(this, { pageNo ->
            viewModal.getPlaceImages(pageNo ?: 1)
        })

        viewModal.isLoading.observe(this, { loading ->
            val pageNo = viewModal.pageNo.value ?: 1L
            if (loading == true) {
                if (pageNo > 1) {
                    adapter.showLoading()
                } else {
                    shimmerFrameLayout?.visibility = View.VISIBLE
                }
            } else {
                if (pageNo > 1) {
                    adapter.hideLoading()
                } else {
                    shimmerFrameLayout?.visibility = View.GONE
                }
            }
        })

        //call api for 1st page
        viewModal.pageNo.value = 1L
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }

    override fun loadMoreItems() {
        viewModal.pageNo.value = viewModal.pageNo.value?.plus(1)
    }

    override val isLastPage: Boolean
        get() = isLast
    override val isLoading: Boolean
        get() = viewModal.isLoading.value ?: false

}