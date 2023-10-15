package com.euvic.awanturaokase

import android.content.res.Resources
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import com.euvic.awanturaokase.snackBar.SnackBarManager
import com.euvic.awanturaokase.snackBar.SnackBarMessage.Companion.toMessage
import com.google.accompanist.systemuicontroller.SystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@Stable
class AppState(
    val systemUiController: SystemUiController,
    val snackBarHostState: SnackbarHostState,
    val snackBarManager: SnackBarManager,
    val navHostController: NavHostController,
    val resources: Resources,
    coroutineScope: CoroutineScope
) {
    init {
        coroutineScope.launch {
            snackBarManager.snackBarMessages.filterNotNull().collect { snackBarMessage ->
                val text = snackBarMessage.toMessage(resources)
                snackBarHostState.showSnackbar(text)
                snackBarManager.clearSnackBarState()
            }
        }
    }

    fun navigate(route: String) {
        navHostController.navigate(route) { launchSingleTop = true }
    }
}