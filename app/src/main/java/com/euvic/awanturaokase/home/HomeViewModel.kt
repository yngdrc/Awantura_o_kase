package com.euvic.awanturaokase.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.euvic.awanturaokase.AppViewModel
import com.euvic.awanturaokase.authentication.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
) : AppViewModel() {
    var uiState = mutableStateOf(HomeUiState())
        private set
}