package lib.composeutils.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ComponentActivity

/**
*   Method to open an activity
*/
fun <T> Context?.openActivity(
    toActivity: Class<T>,
    shouldFinish: Boolean = false,
    extras: Bundle.() -> Unit = {},
) {
    if (this == null)
        return
    val intent = Intent(this, toActivity)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
    if (shouldFinish)
        this.findActivity()?.finish()
}

fun Context.findActivity(): Activity? = when (this) {
    is AppCompatActivity -> this
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}