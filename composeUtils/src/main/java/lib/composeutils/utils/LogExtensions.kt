package lib.composeutils.utils

import android.util.Log

private const val TAG = "ComposeUtils"
fun Any?.loge(tag: String = TAG, filterWord: String = "" ) {
    Log.e(tag, "$filterWord ${this?.toString()}" )
}

fun Any?.logd(tag: String = TAG, filterWord: String = "" ) {
    Log.d(tag, "$filterWord ${this?.toString()}")
}

fun Any?.logi(tag: String = TAG, filterWord: String = "" ) {
    Log.i(tag, "$filterWord ${this?.toString()}")
}

fun Any?.logv(tag: String = TAG, filterWord: String = "" ) {
    Log.v(tag, "$filterWord ${this?.toString()}")
}

fun Any?.logw(tag: String = TAG, filterWord: String = "" ) {
    Log.w(tag, "$filterWord ${this?.toString()}")
}

fun Any?.logwtf(tag: String = TAG, filterWord: String = "" ) {
    Log.wtf(tag, "$filterWord ${this?.toString()}")
}