package com.euvic.awanturaokase.authentication

import androidx.compose.runtime.mutableStateOf
import com.euvic.awanturaokase.AppViewModel
import com.euvic.awanturaokase.R
import com.euvic.awanturaokase.home.HOME_SCREEN_KEY
import com.euvic.awanturaokase.isValidEmail
import com.euvic.awanturaokase.service.AccountService
import com.euvic.awanturaokase.snackBar.SnackBarManager
import com.euvic.awanturaokase.snackBar.SnackBarMessage
import com.google.firebase.auth.AuthResult
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService
) : AppViewModel() {
    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick(action: (String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackBarManager.showMessage(R.string.email_error)
            return
        }

        if (password.isBlank()) {
            SnackBarManager.showMessage(R.string.empty_password_error)
            return
        }

        launchCatching {
            accountService.authenticate(
                email = email,
                password = password,
                onSuccessAction = { authResult ->
                    SnackBarManager.showMessage(
                        message = SnackBarMessage.StringSnackBar(message = "Logged in")
                    ).also {
                        action.invoke("$HOME_SCREEN_KEY/${authResult.user?.email}")
                    }
                },
                onFailureAction = { exception ->
                    SnackBarManager.showMessage(
                        message = SnackBarMessage.StringSnackBar(message = exception.toString())
                    )
                }
            )
        }
    }
}