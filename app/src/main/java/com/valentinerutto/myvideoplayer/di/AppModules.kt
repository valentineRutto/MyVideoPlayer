package com.valentinerutto.myvideoplayer.di

import com.valentinerutto.myvideoplayer.App
import com.valentinerutto.myvideoplayer.data.VideoRepository
import com.valentinerutto.myvideoplayer.data.network.ApiService
import com.valentinerutto.myvideoplayer.data.network.RetrofitClient.createOkClient
import com.valentinerutto.myvideoplayer.data.network.RetrofitClient.createRetrofit
import com.valentinerutto.myvideoplayer.ui.VideoViewModel
import com.valentinerutto.myvideoplayer.utils.Constants
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val networkingModule = module {
    single { App.INSTANCE }

    single<ApiService> { (get() as Retrofit).create(ApiService::class.java) }

    single { createOkClient() }

    single {
        createRetrofit(
            baseUrl = Constants.BASE_URL, get()
        )
    }
}

val repositoryModule = module {
    single { VideoRepository(get()) }
}

val viewmodelModule = module {

    viewModel { VideoViewModel(get()) }

}