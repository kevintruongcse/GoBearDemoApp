package kelvintruong.cse.gobeartechnicaltest.util

import android.content.Context
import android.content.SharedPreferences

object SharePrefUtils {

    enum class Key {
        LAUNCHED, REMEMBER
    }

    // PRIVATE
    private fun getSharePreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getBoolean(context: Context, key: Key): Boolean {
        return getSharePreference(context).getBoolean(key.name, false)
    }

    fun setBoolean(context: Context, key: Key, isBoolean: Boolean) {
        getEditor(context).putBoolean(key.name, isBoolean).apply()
    }

    private fun getEditor(context: Context): SharedPreferences.Editor {
        val sharedPreferences = getSharePreference(context)
        return sharedPreferences.edit()
    }

    // PUBLIC
    fun contain(context: Context, key: Key): Boolean {
        return getSharePreference(context).contains(key.name)
    }

    fun clear(context: Context, key: Key) {
        getSharePreference(context).edit().remove(key.name).apply()
    }

    fun clearAll(context: Context) {
        getSharePreference(context).edit().clear().apply()
    }
}