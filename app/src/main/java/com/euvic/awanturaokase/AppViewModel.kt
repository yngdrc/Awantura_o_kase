package com.euvic.awanturaokase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.euvic.awanturaokase.snackBar.SnackBarManager
import com.euvic.awanturaokase.snackBar.SnackBarMessage.Companion.toSnackBarMessage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class AppViewModel : ViewModel() {
    fun launchCatching(
        snackBar: Boolean = true,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(
        CoroutineExceptionHandler { _, throwable ->
            if (snackBar) {
                SnackBarManager.showMessage(throwable.toSnackBarMessage())
            }
        },
        block = block
    )
}