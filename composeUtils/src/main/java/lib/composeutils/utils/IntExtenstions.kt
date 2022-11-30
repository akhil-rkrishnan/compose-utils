package lib.composeutils.utils

/**
*   Method to get int value as 1st, 2nd, 3rd etc.
 *   @return   string : input 1 -> output 1st, input 22 - > 22nd.. etc
*/
fun Int?.asOrdinal(): String {
    if (this == null) {
        return ""
    }
    return "$this" + if (this in 11..13) {
        "th"
    } else {
        when (this % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }
}