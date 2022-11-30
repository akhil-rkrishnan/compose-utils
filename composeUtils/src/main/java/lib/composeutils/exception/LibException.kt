package lib.composeutils.exception

sealed class LibException(message: String): Exception(message)

class ColorListViolation(val listSize: Int): LibException("Given list size is $listSize, but expected list size >= 2")
