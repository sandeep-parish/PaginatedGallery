package com.machinetask.client

import com.machinetask.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient {

    companion object {
        var apiInstance: RestApis? = null

        fun getInstance(): RestApis {
            if (apiInstance == null) {
                // preparing log level for interceptor.
                val logging = getLoggingInstance()

                // here we are defining client and making configuration for read/connection time out.
                // also adding interceptor which we use for api logging.
                val client = OkHttpClient.Builder()
                    .readTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                        chain.proceed(request.build())
                    }.addInterceptor(logging).build()

                // preparing retrofit builder and adding
                val retrofit = retrofitInstance(client)
                apiInstance = retrofit.create(RestApis::class.java)
            }
            return apiInstance!!
        }

        private fun getLoggingInstance(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }

            return logging
        }

        private fun retrofitInstance(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}