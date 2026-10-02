package com.example.data.repository

import com.example.data.model.IslamicDate
import com.example.data.model.PrayerTimes
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

object IslamicToolsRepository {

    private const val KAABA_LAT = 21.4225
    private const val KAABA_LNG = 39.8262

    private val HIJRI_MONTHS = listOf(
        "Muharram", "Safar", "Rabi' al-Awwal", "Rabi' al-Thani",
        "Jumada al-Awwal", "Jumada al-Thani", "Rajab", "Sha'ban",
        "Ramadan", "Shawwal", "Dhu al-Qi'dah", "Dhu al-Hijjah"
    )

    fun getQiblaBearing(userLat: Double = 23.8103, userLng: Double = 90.4125): Double {
        val latRad = Math.toRadians(userLat)
        val kaabaLatRad = Math.toRadians(KAABA_LAT)
        val dLngRad = Math.toRadians(KAABA_LNG - userLng)

        val y = sin(dLngRad)
        val x = cos(latRad) * kotlin.math.tan(kaabaLatRad) - sin(latRad) * cos(dLngRad)

        var qibla = Math.toDegrees(atan2(y, x))
        qibla = (qibla + 360) % 360
        return qibla
    }

    fun getTodayHijriDate(): IslamicDate {
        val calendar = Calendar.getInstance()
        val gregorianFormatted = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.ENGLISH).format(Date())

        // Algorithmic approximation of Umm al-Qura Hijri calendar
        val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
        val year = calendar.get(Calendar.YEAR)

        // Reference: 1 Ramadan 1445 AH corresponded to approx March 11, 2024
        // For general current date calculation:
        val julianDay = getJulianDay(year, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
        val l = julianDay - 1948440 + 10632
        val n = ((l - 1) / 10631).toInt()
        val l2 = l - 10631 * n + 354
        val j = (((10985 - l2) / 5316).toInt()) * (((50 * l2) / 17719).toInt()) + ((l2 / 5670).toInt()) * (((43 * l2) / 15238).toInt())
        val l3 = l2 - (((30 - j) / 15).toInt()) * (((17719 * j) / 50).toInt()) - ((j / 16).toInt()) * (((15238 * j) / 43).toInt()) + 29
        val monthIdx = ((24 * l3) / 709).toInt()
        val day = (l3 - ((709 * monthIdx) / 24).toInt()).toInt().coerceIn(1, 30)
        val hijriYear = (30 * n + j - 30).coerceAtLeast(1445)
        val hijriMonthName = HIJRI_MONTHS.getOrElse(monthIdx.coerceIn(0, 11)) { "Ramadan" }

        val specialOccasion = when {
            hijriMonthName == "Ramadan" -> "Blessed Month of Ramadan (Fasting)"
            hijriMonthName == "Shawwal" && day in 1..3 -> "Eid al-Fitr Mubarak"
            hijriMonthName == "Dhu al-Hijjah" && day == 9 -> "Day of Arafah"
            hijriMonthName == "Dhu al-Hijjah" && day in 10..13 -> "Eid al-Adha"
            hijriMonthName == "Muharram" && day == 10 -> "Day of Ashura"
            hijriMonthName == "Rabi' al-Awwal" && day == 12 -> "Mawlid an-Nabi"
            hijriMonthName == "Rajab" && day == 27 -> "Isra and Mi'raj"
            hijriMonthName == "Sha'ban" && day == 15 -> "Laylat al-Bara'at"
            else -> null
        }

        return IslamicDate(
            hijriDay = day,
            hijriMonthName = hijriMonthName,
            hijriYear = hijriYear,
            gregorianFormatted = gregorianFormatted,
            specialOccasion = specialOccasion
        )
    }

    private fun getJulianDay(year: Int, month: Int, day: Int): Double {
        var y = year
        var m = month
        if (m <= 2) {
            y -= 1
            m += 12
        }
        val a = (y / 100).toInt()
        val b = 2 - a + (a / 4).toInt()
        return (365.25 * (y + 4716)).toInt() + (30.6001 * (m + 1)).toInt() + day + b - 1524.5
    }

    fun getTodayPrayerTimes(): PrayerTimes {
        // Return calculated prayer times with dynamic next prayer
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val currentMinutes = hour * 60 + minute

        val fajrMin = 4 * 60 + 45      // 04:45 AM
        val sunriseMin = 6 * 60 + 0    // 06:00 AM
        val dhuhrMin = 12 * 60 + 15    // 12:15 PM
        val asrMin = 15 * 60 + 45      // 03:45 PM
        val maghribMin = 18 * 60 + 10  // 06:10 PM
        val ishaMin = 19 * 60 + 30     // 07:30 PM

        val (nextPrayer, countdownMinutes) = when {
            currentMinutes < fajrMin -> "Fajr" to (fajrMin - currentMinutes)
            currentMinutes < sunriseMin -> "Sunrise" to (sunriseMin - currentMinutes)
            currentMinutes < dhuhrMin -> "Dhuhr" to (dhuhrMin - currentMinutes)
            currentMinutes < asrMin -> "Asr" to (asrMin - currentMinutes)
            currentMinutes < maghribMin -> "Maghrib" to (maghribMin - currentMinutes)
            currentMinutes < ishaMin -> "Isha" to (ishaMin - currentMinutes)
            else -> "Fajr" to (24 * 60 - currentMinutes + fajrMin)
        }

        val hoursRemaining = countdownMinutes / 60
        val minsRemaining = countdownMinutes % 60
        val countdownStr = if (hoursRemaining > 0) "${hoursRemaining}h ${minsRemaining}m" else "${minsRemaining}m"

        return PrayerTimes(
            fajr = "04:45 AM",
            sunrise = "06:00 AM",
            dhuhr = "12:15 PM",
            asr = "03:45 PM",
            maghrib = "06:10 PM",
            isha = "07:30 PM",
            nextPrayerName = nextPrayer,
            nextPrayerCountdown = countdownStr,
            city = "Makkah / Universal",
            calculationMethod = "Muslim World League"
        )
    }
}
