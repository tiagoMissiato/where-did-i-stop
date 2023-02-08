package com.tiagomissiato.wheredidistop.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class BearerInterceptor @Inject constructor() : Interceptor {
    object Auth {
        const val BEARER = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMzI4ODhjOTdjNWRkZTczOWFiMWM2ODQ1ZGM5OGM4ZCIsInN1YiI6IjYzZGE4MmZjM2RjMzEzMDA3ZDgxMGU1MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.fQc1poRqpdOiA5EkZ7YCx5eQPbyks2VRWOWQsBe2fjg"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain
            .request()
            .newBuilder()
            .addHeader("Authorization", "Bearer ${Auth.BEARER}")
            .build()

        return chain.proceed(newRequest)
    }
}