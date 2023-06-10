package com.example.paging3

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object BrandDi {
    val Token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiIxOCIsInVuaXF1ZV9uYW1lIjoiQU1LIFNFTExFUiIsImdyb3Vwc2lkIjoiMSIsInJvbGUiOiIxIiwicHJpbWFyeXNpZCI6IjUiLCJuYmYiOjE2ODQxMzY2NDYsImV4cCI6MTcxNTY3MjY0NiwiaWF0IjoxNjg0MTM2NjQ2LCJpc3MiOiJtaHMifQ.4p4W6t4I6QwpZTCVt_f7Ke5zoSvupn-KcEyTaCJduZKjhriCOc0nyIN6LssEf4yQPdo81CFKpEbHm4qDPQTWRA"

    @Provides
    @Singleton
    fun provideLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    @Provides
    @Singleton
    fun provideCommonOkHttpClient(logging : HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthenticationInterceptor(Token))
            .retryOnConnectionFailure(false)
            .addInterceptor(logging)
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://vetail-product-entry.shopdoora.com/api/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun providesCommonProductLiveApiService(retrofit: Retrofit) : BrandApi =
        retrofit.create(BrandApi::class.java)

    @Provides
    @Singleton
    fun providesVetailBrandRepository(brandApi: BrandApi):BrandRepository{
        return BrandRepositoryImpl(brandApi)
    }
}