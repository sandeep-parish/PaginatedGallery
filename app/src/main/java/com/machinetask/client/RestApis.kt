package com.machinetask.client

import com.machinetask.modals.PlacesModal
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApis {

    @GET(ApiParam.LIST_API_TAG)
    fun getPlacesList(@Query(ApiParam.KEY_PAGE_NO) pageNo: Long): Observable<Response<List<PlacesModal>>>

}