package com.dominoconquista.domain.repository

import com.google.firebase.auth.FirebaseUser


interface AuthRepository {
    suspend fun loginWithEmail(email: String, pass: String): Result<FirebaseUser>
    suspend fun registerWithEmail(email: String, pass: String): Result<FirebaseUser>
    suspend fun loginWithGoogle(idToken: String): Result<FirebaseUser>
    fun getCurrentUser(): FirebaseUser?
}