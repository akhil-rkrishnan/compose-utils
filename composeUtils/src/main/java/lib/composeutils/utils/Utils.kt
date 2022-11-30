package lib.composeutils.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.text.isDigitsOnly

/**
* Method to open the app settings
*/
fun Context.openAppSettings() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    val uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    this.startActivity(intent)
}

/**
 * Method to get current app version name as int
 */
fun Context.getVersionNameAsInt(): Int = try {
    val manager = this.packageManager
    val info = manager.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES)
    val versionName = info.versionName
    val versionNameAsInt = versionName.replace(".", "")
    if (versionNameAsInt.isDigitsOnly())
        versionNameAsInt.toInt()
    else
        -1
} catch (e: PackageManager.NameNotFoundException) {
    e.printStackTrace()
    -1
} catch (e: Exception) {
    e.printStackTrace()
    -1
}