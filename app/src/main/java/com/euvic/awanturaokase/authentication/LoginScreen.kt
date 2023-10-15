package com.euvic.awanturaokase.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.euvic.awanturaokase.composable.BasicButton
import com.euvic.awanturaokase.composable.EmailField
import com.euvic.awanturaokase.composable.PasswordField
import androidx.hilt.navigation.compose.hiltViewModel
import com.euvic.awanturaokase.R
import com.euvic.awanturaokase.basicButton
import com.euvic.awanturaokase.fieldModifier

@Composable
fun LoginScreen(
    action: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    Column(
        modifier = modifier.fillMaxWidth().fillMaxHeight().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(uiState.email, viewModel::onEmailChange, Modifier.fieldModifier())
        PasswordField(uiState.password, viewModel::onPasswordChange, Modifier.fieldModifier())
        BasicButton(R.string.sign_in, Modifier.basicButton()) {
            viewModel.onSignInClick(action)
        }
    }
}