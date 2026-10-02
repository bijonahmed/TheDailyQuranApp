# The Daily Quran — Android Application

A modern, peaceful, and complete native Android application for **The Daily Quran** (https://thedailyquran.com/).
Tagline: *Read, Listen & Learn the Holy Quran*

---

## 🌟 Key Features Implemented

### 1. Home Dashboard
- **Continue Reading Card**: Real-time display of last-read Surah and Ayah with one-tap resumption.
- **Verse of the Day**: Authentic Arabic text, transliteration, English translation, and audio recitation button.
- **Prayer Times Glimpse**: Next prayer countdown and 5 daily prayer timings with lunar Hijri date.
- **Hadith of the Day**: Authentic Hadith with Arabic, translation, narrator, and reference.
- **Daily Supplication (Dua)**: Meaningful daily prayer from Hisnul Muslim.
- **Daily Islamic Quote**: Inspirational guidance from the companions and scholars.
- **Quick Action Hub**: Direct shortcuts to Tasbeeh, Qibla, Duas, Hijri Calendar, Kids Zone, Prophets Tree, and Articles.

### 2. Complete Quran Reader
- **All 114 Surahs & 30 Juz**: Fully mapped with revelation type (Meccan/Medinan), Arabic names, and Ayah counts.
- **Authentic Uthmani Arabic Script**: High-legibility typography with Ayah end markers.
- **English Translation**: Sahih International translation alongside verses.
- **Customizable Typography**: Real-time font size sliders for both Arabic script and English translation.
- **Verse Actions**: Play individual Ayah recitation, copy to clipboard, share, and toggle bookmarks.
- **Surah Recitation**: Play continuous recitation of the complete Surah.

### 3. Quran Audio Recitation (Media3 ExoPlayer)
- Integration with Android **Media3 ExoPlayer**.
- Play, pause, skip to next/previous Ayah, repeat mode.
- Reciter selection:
  - *Mishary Rashid Alafasy*
  - *AbdulBaset AbdulSamad (Murattal)*
  - *Maher Al-Muaiqly*
  - *Abdur-Rahman as-Sudais*
- Floating mini-player bar visible across screens during audio playback.

### 4. Hadith Collections
- Verified collections: **Sahih al-Bukhari**, **Sahih Muslim**, and **40 Hadith Nawawi**.
- Searchable by keywords, topics, or narrator.
- Authentic Arabic text, English translation, grade, and book reference.
- Copy and share functionality.

### 5. Islamic Duas (Hisnul Muslim)
- Categorized library: *Morning & Evening*, *Prayer*, *Protection*, *Travel*, *Forgiveness*, and *Family*.
- Arabic text, English transliteration, translation, and reference sources.

### 6. Digital Tasbeeh Counter
- Tap anywhere on the large counter circle to increment.
- Presets: *SubhanAllah*, *Alhamdulillah*, *Allahu Akbar*, *La ilaha illallah*, *Astaghfirullah*, and custom dhikr.
- Target settings (33, 99, 100, 1000).
- Haptic feedback and reset confirmation.
- Offline lifetime statistics and session history saved in local Room database.

### 7. Islamic Tools
- **Qibla Direction**: Spherical trigonometry bearing towards the Holy Ka'bah in Makkah with interactive compass dial.
- **Prayer Times Calculator**: Accurate schedules for Fajr, Sunrise, Dhuhr, Asr, Maghrib, and Isha with next prayer countdown.
- **Islamic Calendar**: Umm al-Qura lunar dates, month navigation, and important Islamic occasions (Ramadan, Eid al-Fitr, Eid al-Adha, Ashura, Laylat al-Qadr).
- **Islamic Names Directory**: Searchable Muslim boy and girl names with original Arabic spelling and detailed meanings.

### 8. Kids Zone & Educational Modules
- **Kids Arabic Alphabet**: Cards for all 28 letters (Alif to Ya) with phonetics, example words, and meanings.
- **Interactive Islamic Quiz**: Questions on the Quran and Islam with instant scoring, feedback, and explanations.
- **Prophets Tree**: Chronological timeline of all 25 Prophets mentioned in the Quran from Adam to Muhammad (PBUH).

### 9. Articles & Books
- Islamic articles and classic books exploring Quranic preservation, virtues of Surah Al-Kahf, and etiquette of Dua, with direct links to the official website: https://thedailyquran.com/

### 10. Local Room Database & Preferences
- Entities for **Bookmarks**, **Last Read**, **Favorites**, and **Tasbeeh Sessions**.
- Reactive UI powered by Kotlin Coroutines & Flow.
- Offline-first architecture: All core features function without an active internet connection.

---

## 🛠️ Technology Stack & Architecture

- **Language:** Kotlin 2.2.10
- **UI Toolkit:** Jetpack Compose with Material 3 Design
- **Architecture:** Clean MVVM (Model-View-ViewModel) + Repository Pattern
- **Local Database:** Room 2.7.0 (with KSP)
- **Audio Engine:** Android Media3 ExoPlayer 1.5.1
- **Networking:** Retrofit 2.12.0 + Moshi + OkHttp
- **Preferences:** Shared Preferences & StateFlow
- **Theme:** Brand identity with Emerald Green (`#0D472B`), Warm Islamic Gold (`#C5A059`), Soft Cream, and full Dark Mode support.

---

## 🚀 How to Open and Run in Android Studio

1. Open Android Studio (Ladybug / Koala or newer recommended).
2. Select **Open** and choose the root directory of this project.
3. Allow Gradle to sync dependencies.
4. Run the project on an emulator (Android 7.0+ / API 24+) or a connected physical Android device.

### Build Debug APK:
```bash
gradle :app:assembleDebug
```
Output location:
`app/build/outputs/apk/debug/app-debug.apk`

### Build Signed Release AAB (Android App Bundle):
```bash
gradle :app:bundleRelease
```
Output location:
`app/build/outputs/bundle/release/app-release.aab`

---

## 🔒 Permissions & Privacy
- `INTERNET` & `ACCESS_NETWORK_STATE`: For online Quran recitation audio streaming and remote Surah data fetch.
- `VIBRATE`: For subtle haptic feedback during Tasbeeh clicks (can be toggled in settings).
- `FOREGROUND_SERVICE` & `FOREGROUND_SERVICE_MEDIA_PLAYBACK`: For continuous audio recitation.
- Zero tracking, zero third-party ads in holy text, and zero personal data collection.
