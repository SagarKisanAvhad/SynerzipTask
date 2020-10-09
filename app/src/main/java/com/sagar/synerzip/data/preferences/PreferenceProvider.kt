package com.sagar.synerzip.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private const val KEY_SAVED_AT = "key_saved_at"

class PreferenceProvider(context: Context) {
    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)


    fun saveSavedAt(savedAt: String) {
        preference.edit().putString(KEY_SAVED_AT, savedAt).apply()
    }


    fun getSavedAt(): String? = preference.getString(KEY_SAVED_AT, null)


    fun saveSavedAt(city: String, savedAt: String) {
        preference.edit().putString(city, savedAt).apply()
    }


    fun getSavedAt(city: String): String? = preference.getString(city, null)

}