package lib.composeutils.utils

import timber.log.Timber

private const val TAG = "LogExtensions"
fun Any?.loge(tag: String = TAG, filterWord: String = "" ) {
    Timber.tag(tag).e("%s%s", filterWord, this?.toString())
}

fun Any?.logd(tag: String = TAG, filterWord: String = "" ) {
    Timber.tag(tag).d("%s%s", filterWord, this?.toString())
}

fun Any?.logi(tag: String = TAG, filterWord: String = "" ) {
    Timber.tag(tag).i("%s%s", filterWord, this?.toString())
}

fun Any?.logv(tag: String = TAG, filterWord: String = "" ) {
    Timber.tag(tag).v("%s%s", filterWord, this?.toString())
}

fun Any?.logw(tag: String = TAG, filterWord: String = "" ) {
    Timber.tag(tag).w("%s%s", filterWord, this?.toString())
}

fun Any?.logwtf(tag: String = TAG, filterWord: String = "" ) {
    Timber.tag(tag).wtf("%s%s", filterWord, this?.toString())
}