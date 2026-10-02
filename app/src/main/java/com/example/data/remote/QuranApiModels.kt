package com.example.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuranApiResponse<T>(
    @Json(name = "code") val code: Int,
    @Json(name = "status") val status: String,
    @Json(name = "data") val data: T
)

@JsonClass(generateAdapter = true)
data class SurahDto(
    @Json(name = "number") val number: Int,
    @Json(name = "name") val name: String,
    @Json(name = "englishName") val englishName: String,
    @Json(name = "englishNameTranslation") val englishNameTranslation: String,
    @Json(name = "numberOfAyahs") val numberOfAyahs: Int,
    @Json(name = "revelationType") val revelationType: String
)

@JsonClass(generateAdapter = true)
data class AyahDto(
    @Json(name = "number") val number: Int,
    @Json(name = "text") val text: String,
    @Json(name = "numberInSurah") val numberInSurah: Int,
    @Json(name = "juz") val juz: Int?
)

@JsonClass(generateAdapter = true)
data class SurahEditionDetailDto(
    @Json(name = "number") val number: Int,
    @Json(name = "name") val name: String,
    @Json(name = "englishName") val englishName: String,
    @Json(name = "ayahs") val ayahs: List<AyahDto>
)
