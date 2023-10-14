package com.euvic.awanturaokase

import android.content.res.Resources
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.euvic.awanturaokase.snackBar.SnackBarManager
import com.euvic.awanturaokase.ui.theme.AwanturaOKasęTheme
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen() {
    AwanturaOKasęTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val appState = rememberAppState()

            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = appState.snackBarHostState,
                        modifier = Modifier.padding(8.dp),
                        snackbar = { snackBarData ->
                            Snackbar(
                                snackBarData,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    )
                }
            ) { innerPaddingModifier ->
                NavHost(
                    navController = appState.navHostController,
                    startDestination = LOGIN_SCREEN_KEY,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    makeNavGraph(appState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    snackBarManager: SnackBarManager = SnackBarManager,
    navHostController: NavHostController = rememberNavController(),
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(snackBarHostState, snackBarManager, navHostController, resources, coroutineScope) {
    AppState(snackBarHostState, snackBarManager, navHostController, resources, coroutineScope)
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

fun NavGraphBuilder.makeNavGraph(appState: AppState) {
    composable(LOGIN_SCREEN_KEY) {
        LoginScreen(action = { route -> appState.navigate(route = route) })
    }
}