package ak.composeapp.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

private val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

fun runOnUiThread(block: suspend () -> Unit) = uiScope.launch { block() }