// Archivo: app/src/main/java/com/dominoconquista/data/repository/AuthRepositoryImpl.kt
package com.dominoconquista.data.repository

import com.dominoconquista.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {

    override suspend fun signInWithEmail(email: String, pass: String): Result<String> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, pass).await()
            Result.success(result.user?.uid ?: throw Exception("User null"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUpWithEmail(email: String, pass: String): Result<String> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, pass).await()
            Result.success(result.user?.uid ?: throw Exception("User null"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // El login con Google requiere que recibas el idToken desde la UI (Activity/Fragment)
    override suspend fun signInWithGoogle(idToken: String): Result<String> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()
            Result.success(result.user?.uid ?: throw Exception("User null"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}