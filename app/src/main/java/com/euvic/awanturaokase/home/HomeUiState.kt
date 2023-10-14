package com.euvic.awanturaokase.home

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.auth.User

data class HomeUiState(
    val firebaseUser: FirebaseUser? = null
)