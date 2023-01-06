package lib.composeutils.ui

import android.content.Context
import android.widget.Toast

/**
* Show toast with provided message
 * @param message message to display
 * @param longToast for long toast
*/
fun Context?.showToast(message: String, longToast: Boolean = false) {
    if (this == null || message.isEmpty() || message.isBlank())
        return
    Toast.makeText(this, message, if (longToast) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}

/**
* Show toast with provided message (from string resource / literal string)
 * @param uiMessage Ui message class for setting string from resource / literal
 * @param longToast for long toast
*/
fun Context.showToast(uiMessage: UiMessage, longToast: Boolean = false) {
    when (uiMessage) {
        is UiMessage.FromStringRes -> {
            Toast.makeText(
                this,
                this.getString(uiMessage.res),
                if (longToast) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
            ).show()
        }
        is UiMessage.FromString -> {
            showToast(uiMessage.message, longToast)
        }
    }
}

/**
* Sealed class for Ui message string
*/
sealed class UiMessage {
    data class FromStringRes(val res: Int) : UiMessage()
    data class FromString(val message: String) : UiMessage()
}