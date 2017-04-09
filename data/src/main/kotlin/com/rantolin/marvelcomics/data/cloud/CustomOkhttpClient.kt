package com.rantolin.marvelcomics.data.cloud

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by ricar on 8/4/17.
 */
class CustomOkhttpClient {
    companion object{
        fun getInstance(): OkHttpClient {
            return OkHttpClient.Builder()
                    .addInterceptor(getLogginInterceptor())
                    .addInterceptor(RequestInterceptor())
                    .build()

        }
        private fun getLogginInterceptor(): Interceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }
    }


}