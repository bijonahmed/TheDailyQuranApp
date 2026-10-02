package com.example.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface QuranApiService {
    @GET("v1/surah")
    suspend fun getSurahs(): QuranApiResponse<List<SurahDto>>

    @GET("v1/surah/{surahNumber}/editions/quran-uthmani,en.sahih")
    suspend fun getSurahVerses(
        @Path("surahNumber") surahNumber: Int
    ): QuranApiResponse<List<SurahEditionDetailDto>>
}

object NetworkClient {
    private const val BASE_URL = "https://api.alquran.cloud/"

    private val moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        })
        .build()

    val quranApiService: QuranApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(QuranApiService::class.java)
    }
}
