package lib.composeutils.utils

fun String?.isValid(): Boolean {
    if (this == null)
        return false
    val trimmedText = this.trim()
    return trimmedText.isNotEmpty()
}

fun String?.isNotValid(): Boolean {
    return !this.isValid()
}

fun String?.containsEmoji(): Boolean {
    if (this == null)
        return false
    this.forEach {
        if (it.isSurrogate())
            return true
    }
    return false
}