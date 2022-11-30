package lib.composeutils.exception

sealed class IllegalException(message: String): IllegalAccessException(message)

class ColorListViolation(val listSize: Int): IllegalException("Given list size is $listSize, but expected list size >= 2")
