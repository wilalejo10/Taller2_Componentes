package com.dominoconquista.domain.repository

import com.dominoconquista.data.model.GameDtos
import com.dominoconquista.domain.model.GameState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : RoomRepository {

    override fun observeRoom(roomId: String): Flow<GameState> = callbackFlow {
        val listenerRegistration = firestore.collection("rooms").document(roomId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.exists()) {
                    val gameDtos = snapshot.toObject(GameDtos::class.java)
                    // Asumiendo que tienes una función de extensión toDomain() en tus mappers
                    gameDtos?.let { trySend(it.toDomain()) }
                }
            }

        // viewmodel cancela la recolección del Flow
        awaitClose { listenerRegistration.remove() }
    }
}