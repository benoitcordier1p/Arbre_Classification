package com.example.data.di

import android.content.Context
import com.example.data.remote.TreeApi
import com.example.data.util.URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideTreeApi(@ApplicationContext appContext: Context): TreeApi {
        return Retrofit.Builder()
                .baseUrl(URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHTTPClient(appContext))
                .build()
                .create(TreeApi::class.java)
    }

    private fun getHTTPClient(context: Context) : OkHttpClient{
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)
        return OkHttpClient
            .Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build()
                chain.proceed(request)
            }.build()
    }
}