package com.euvic.awanturaokase.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.euvic.awanturaokase.R
import com.euvic.awanturaokase.authentication.LoginViewModel
import com.euvic.awanturaokase.basicButton
import com.euvic.awanturaokase.composable.BasicButton
import com.euvic.awanturaokase.composable.BasicField
import com.euvic.awanturaokase.composable.EmailField
import com.euvic.awanturaokase.composable.PasswordField
import com.euvic.awanturaokase.fieldModifier
import com.google.firebase.auth.FirebaseUser
import com.google.gson.GsonBuilder

const val HOME_SCREEN_KEY = "HomeScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    email: String?,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    Column(
        modifier = modifier.fillMaxWidth().fillMaxHeight().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email.toString(),
            onValueChange = {},
            readOnly = true
        )
    }
}