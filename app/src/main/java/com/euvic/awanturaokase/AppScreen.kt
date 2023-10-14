@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.euvic.awanturaokase.authentication.LOGIN_SCREEN_KEY
import com.euvic.awanturaokase.authentication.LoginScreen
import com.euvic.awanturaokase.home.HOME_SCREEN_KEY
import com.euvic.awanturaokase.home.HomeScreen
import com.euvic.awanturaokase.snackBar.SnackBarManager
import com.euvic.awanturaokase.ui.theme.AwanturaOKasęTheme
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
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
    systemUiController: SystemUiController = rememberSystemUiController(),
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    snackBarManager: SnackBarManager = SnackBarManager,
    navHostController: NavHostController = rememberNavController(),
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(
    systemUiController,
    snackBarHostState,
    snackBarManager,
    navHostController,
    resources,
    coroutineScope
) {
    AppState(
        systemUiController,
        snackBarHostState,
        snackBarManager,
        navHostController,
        resources,
        coroutineScope
    )
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.makeNavGraph(appState: AppState) {
    composable(route = LOGIN_SCREEN_KEY) {
        LoginScreen(action = { route -> appState.navigate(route = route) })
    }

    composable(
        route = "$HOME_SCREEN_KEY/{email}",
        arguments = listOf(
            navArgument(name = "email") {
                type = NavType.StringType
                nullable = true
            }
        )
    ) { backStackEntry ->
        HomeScreen(
            email = backStackEntry.arguments?.getString("email")
        )
    }
}