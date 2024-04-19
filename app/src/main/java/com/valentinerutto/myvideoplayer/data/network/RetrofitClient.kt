package com.valentinerutto.myvideoplayer.data.network

import com.valentinerutto.myvideoplayer.utils.Constants
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    @OptIn(ExperimentalSerializationApi::class)
    fun createRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun createOkClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(getLoggingInterceptor()).addInterceptor(
            accessKeyInterceptor(Constants.ACCESS_KEY)
        ).build()
    }

    private fun getLoggingInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return httpLoggingInterceptor
    }

    fun accessKeyInterceptor(accessKey: String): Interceptor {
        return Interceptor { chain ->
            intercept(chain, accessKey)

        }

    }

    fun intercept(chain: Interceptor.Chain, accessKey: String): okhttp3.Response {
        val originalRequest: Request = chain.request()
        val requestBuilder = originalRequest.newBuilder().header("AccessKey", accessKey)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}