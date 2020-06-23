package com.haodong.practice.common.sp

import android.content.Context
import java.lang.IllegalArgumentException
import java.security.Key
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * created by linghaoDo on 2020/6/23
 * description:
 *
 * version:
 */
class Preference<T>(
    val context: Context,
    val name: String,
    val default: T,
    val prefName: String = "default",
    val commitByApply: Boolean
) :
    ReadWriteProperty<Any?, T> {

    private val prefs by lazy {
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(findProperName(property))
    }

    private fun findPreference(key: String): T {
        return when (default) {
            is Long -> prefs.getLong(key, default)
            is Int -> prefs.getInt(key, default)
            is Boolean -> prefs.getBoolean(key, default)
            is String -> prefs.getString(key, default)
            else -> throw IllegalArgumentException("Unsupport type")
        } as T
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(findProperName(property), value)
    }

    private fun putPreference(key: String, value: T) {

        if (commitByApply) {
            with(prefs.edit()) {
                when (value) {
                    is Long -> putLong(key, value)
                    is Int -> putInt(key, value)
                    is Boolean -> putBoolean(key, value)
                    is String -> putString(key, value)
                    else -> throw IllegalArgumentException("Unsupported type.")
                }
            }.apply()
        } else {
            with(prefs.edit()) {
                when (value) {
                    is Long -> putLong(key, value)
                    is Int -> putInt(key, value)
                    is Boolean -> putBoolean(key, value)
                    is String -> putString(key, value)
                    else -> throw IllegalArgumentException("Unsupported type.")
                }
            }.commit()
        }


    }

    private fun findProperName(property: KProperty<*>) = if (name.isEmpty()) property.name else name


}