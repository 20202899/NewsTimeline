package com.carlos.news.framework

import android.app.Application
import com.carlos.core.data.NewsDataSource
import com.carlos.core.data.NewsDataSourceRepository
import com.carlos.core.interactors.GetTopHeadLines
import com.carlos.news.framework.repository.NewsRepository
import com.carlos.news.framework.services.NewsServices
import com.carlos.news.presentation.news.NewsViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {

    fun providerTopHeadLinesServices(retrofit: Retrofit) = retrofit.create(NewsServices::class.java)

    factory { GsonConverterFactory.create() }
    factory { CoroutineCallAdapterFactory() }

    single {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<CoroutineCallAdapterFactory>())
            .build()
    }

    factory { providerTopHeadLinesServices(get()) }
    factory<NewsDataSource> { NewsRepository(get()) }
    factory { NewsDataSourceRepository(get()) }
    single {
        Interactors(
            GetTopHeadLines(get())
        )
    }

    viewModel { NewsViewModel(get()) }
}

class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@NewsApplication)
            modules(mainModule)
        }
    }
}