package com.dominoconquista.domain.repository

import com.dominoconquista.data.model.GameDtos
import com.dominoconquista.domain.model.Placement
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GameRepository @Inject private constructor(
    private val firestore: FirebaseFirestore
) : GameRepository {

    override suspend fun playTile(roomId: String, playerId: String, placement: Placement): Result<Unit> {
        val roomRef = firestore.collection("rooms").document(roomId)

        return try {
            firestore.runTransaction { transaction ->
                val snapshot = transaction.get(roomRef)
                val gameState = snapshot.toObject(GameDtos::class.java)
                    ?: throw FirebaseFirestoreException(
                        "Sala no encontrada",
                        FirebaseFirestoreException.Code.NOT_FOUND
                    )

                // 1. Validar que sea el turno del jugador actual
                if (gameState.currentPlayerTurnId != playerId) {
                    throw Exception("No es tu turno de jugar")
                }

                // 2. Determinar el siguiente turno (lógica simplificada)
                val nextPlayerId = gameState.players.first { it != playerId }

                // 3. Aplicar los cambios de manera atómica
                transaction.update(
                    roomRef,
                    mapOf(
                        "currentPlayerTurnId" to nextPlayerId,
                        // Aquí enviarías el DTO de la ficha jugada y las casillas conquistadas
                        "lastActionTime" to FieldValue.serverTimestamp()
                    )
                )
            }.await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
