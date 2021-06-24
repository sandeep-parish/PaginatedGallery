package com.machinetask.client

object ApiParam {
    const val API_PATH = "v2/"

    const val HTTP_SUCCESS_STATUS = 200
    const val HTTP_VALIDATION_ERROR = 400
    const val HTTP_UNAUTHORIZED_USER = 401
    const val HTTP_UNVERIFIED_USER = 402
    const val HTTP_DEACTIVED_USER = 403
    const val HTTP_DELETED_USER = 405
    const val HTTP_MAINTAINANCE_MODE = 503

    const val LIST_API_TAG = "${API_PATH}list"

    const val KEY_PAGE_NO = "page"

}