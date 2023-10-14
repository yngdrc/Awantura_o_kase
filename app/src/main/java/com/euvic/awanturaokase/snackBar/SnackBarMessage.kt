package com.euvic.awanturaokase.snackBar

import android.content.res.Resources
import androidx.annotation.StringRes
import com.euvic.awanturaokase.R

sealed class SnackBarMessage {
    class StringSnackBar(val message: String) : SnackBarMessage()
    class ResourceSnackBar(@StringRes val message: Int) : SnackBarMessage()

    companion object {
        fun SnackBarMessage.toMessage(resources: Resources): String {
            return when (this) {
                is StringSnackBar -> this.message
                is ResourceSnackBar -> resources.getString(this.message)
            }
        }

        fun Throwable.toSnackBarMessage(): SnackBarMessage {
            val message = this.message.orEmpty()
            return if (message.isNotBlank()) StringSnackBar(message)
            else ResourceSnackBar(R.string.generic_error)
        }
    }
}