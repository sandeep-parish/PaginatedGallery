package com.machinetask.viewmodal

import androidx.lifecycle.ViewModel
import com.machinetask.SingleLiveEvent
import com.machinetask.client.ApiClient
import com.machinetask.modals.PlacesModal
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModal() : ViewModel() {
    val placesListEvent = SingleLiveEvent<List<PlacesModal>>()
    val pageNo = SingleLiveEvent<Long>()
    val isLoading = SingleLiveEvent<Boolean>()

    fun getPlaceImages(pageNo: Long) {
        isLoading.value = true
        ApiClient.getInstance()
            .getPlacesList(pageNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                if (result.isSuccessful) {
                    val placesList = result.body()
                    placesListEvent.value = placesList
                }
                isLoading.value = false
            }, { error ->
                isLoading.value = false
                //ErrorUtil.setApiError(error, context)
            })
    }
}