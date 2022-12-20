package lib.composeutils.utils

import android.content.SharedPreferences
import timber.log.Timber

fun <T: Any> SharedPreferences.saveData(key: String, value: T) {
    val editor = edit()
    editor.putString(key, value.toJsonString()).apply()
}

fun <T: Any> SharedPreferences.getData(key: String, classType: Class<T>): T? {
   return try {
        val data = getString(key, null)
        if (!data.isNullOrEmpty()) {
            data.fromJson(classType)
        } else {
            null
        }
    } catch (e: Exception) {
        e.message.loge()
        null
    }
}

