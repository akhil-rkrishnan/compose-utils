package ak.lib.composeutils.ui

import android.content.Context
import android.widget.Toast

fun Context?.showToast(message: String, longToast: Boolean = false) {
    if (this == null || message.isEmpty() || message.isBlank())
        return
    Toast.makeText(this, message, if (longToast) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}