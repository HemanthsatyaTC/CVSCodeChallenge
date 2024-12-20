package com.hemanth.cvscodechallenge.di

import com.google.gson.Gson
import com.hemanth.cvscodechallenge.data.remote.ImageDetails
import com.hemanth.cvscodechallenge.data.remote.ImageDetailsInterface
import com.hemanth.cvscodechallenge.data.repository.Repository
import com.hemanth.cvscodechallenge.data.repository.RepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun getGson(): Gson {
        return Gson()
    }

    @Provides
    fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ImageDetails.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun ImageDetailsInterface(
        retrofit: Retrofit
    ): ImageDetailsInterface {
        return retrofit.create(ImageDetailsInterface::class.java)

    }

    @Provides
    fun getRepositoryTinder(
        imageDetails: ImageDetailsInterface
    ): Repository {
        return RepositoryImplementation(
            imageDetails = imageDetails
        )
    }
}