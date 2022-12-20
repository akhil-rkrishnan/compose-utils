package lib.composeutils.utils

import com.google.gson.Gson

fun <T : Any> String?.fromJson(destination: Class<T>): T {
    return Gson().fromJson(this, destination)
}
fun <T> T.toJsonString(): String {
    return Gson().toJson(this)
}
