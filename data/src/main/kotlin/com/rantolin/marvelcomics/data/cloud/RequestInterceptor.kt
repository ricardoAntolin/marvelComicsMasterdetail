package com.rantolin.marvelcomics.data.cloud

import com.rantolin.marvelcomics.data.utils.ToMD5
import com.rantolin.marvelcomics.data.utils.toTimeString
import okhttp3.Interceptor
import okhttp3.Response
import java.sql.Timestamp
import java.util.*

/**
 * Created by ricar on 8/4/17.
 */
class RequestInterceptor: Interceptor {
    private val ts = Timestamp(Date().time).toTimeString()
    private val hashString = ts + PRIVATE_KEY + PUBLIC_KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url().newBuilder()
                .addQueryParameter(TS, ts)
                .addQueryParameter(API_KEY, PUBLIC_KEY)
                .addQueryParameter(HASH,hashString.ToMD5())
                .build()
        return chain.proceed(request.newBuilder().url(url).build())
    }
}

