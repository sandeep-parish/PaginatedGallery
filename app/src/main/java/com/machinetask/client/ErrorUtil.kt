package com.machinetask.client

import android.content.Context

object ErrorUtil {
    val NO_INTERNET = "No internet connection."
    val SERVER_NOT_RESPONDING = "Server not responding."
    val SERVER_TIMEOUT = "Server timeout error."
    val SOMETHING_WENT_WRONG = "Something has gone wrong."

    fun setApiError(t: Throwable?, context: Context) {

//        if (mActivity.checkNetworkConnection()) {
//            if (t != null) {
//                if (t is IndexOutOfBoundsException) {
////                    mActivity.showCustomDialog(mActivity.resources.getString(R.string.api_error_something_wrong))
//                } else if (t is TimeoutException) {
//                    mActivity.showCustomDialog(mActivity.resources.getString(R.string.api_error_server_timeout))
//                } else if (t is UnknownHostException || t is NetworkErrorException) {
//                    mActivity.showCustomDialog(mActivity.resources.getString(R.string.no_interent))
//                } else if (t is IOException || t is ConnectException) {
//                    mActivity.showCustomDialog(mActivity.resources.getString(R.string.api_error_something_wrong))
//                } else if (t is IllegalStateException) {
////                    mActivity.showCustomDialog(mActivity.resources.getString(R.string.api_error_server_not_responding))
//                } else {
////                    mActivity.showCustomDialog(mActivity.resources.getString(R.string.api_error_something_wrong))
//                }
//                Log.d("TRACK ERROR", "Track Error" + t.message)
//                t.printStackTrace()
//                Log.getStackTraceString(t)
//            }
//
//        } else {
//        }
    }

//    fun takeActionOnApiError(response: Response<*>?, mActivity: BaseActivity) {
//        val error = ErrorUtil.parseError(response)
//        when (error.status) {
//            ApiParam.HTTP_UNAUTHORIZED_USER, ApiParam.HTTP_DEACTIVED_USER, ApiParam.HTTP_DELETED_USER -> {
//                // force logout
//                mActivity.logout()
//            }
//
//            ApiParam.HTTP_MAINTAINANCE_MODE -> {
//                mActivity.goToMaintenance()
//            }
//        }
//
//        if (!error.message.isNullOrBlank()) {
//            mActivity.showCustomAlert(error.message)
//        } else {
//            mActivity.handleUnknownError()
//        }
//    }


}