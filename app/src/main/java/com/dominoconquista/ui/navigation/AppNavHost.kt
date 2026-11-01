package com.dominoconquista.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dominoconquista.ui.screens.FakeScreenData
import com.dominoconquista.ui.screens.auth.LoginScreen
import com.dominoconquista.ui.screens.auth.RegisterScreen
import com.dominoconquista.ui.screens.auth.StartScreen
import com.dominoconquista.ui.screens.game.FakeData
import com.dominoconquista.ui.screens.game.GameScreen
import com.dominoconquista.ui.screens.history.HistoryScreen
import com.dominoconquista.ui.screens.lobby.LobbyScreen
import com.dominoconquista.ui.screens.result.ResultScreen
import com.dominoconquista.ui.screens.room.NewGameScreen
import com.dominoconquista.ui.screens.room.WaitingRoomScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    var playerName by remember { mutableStateOf("Jugador") }
    var avatarIndex by remember { mutableStateOf(0) }
    var roomCode by remember { mutableStateOf("A7K3") }
    var maxPlayers by remember { mutableStateOf(4) }

    val goToLobby: () -> Unit = {
        navController.navigate(Routes.LOBBY) {
            popUpTo(Routes.START) { inclusive = true }
        }
    }

    val backToLobby: () -> Unit = {
        navController.popBackStack(Routes.LOBBY, inclusive = false)
    }

    NavHost(navController = navController, startDestination = Routes.START) {

        composable(Routes.START) {
            StartScreen(
                onLogin = { navController.navigate(Routes.LOGIN) },
                onRegister = { navController.navigate(Routes.REGISTER) }
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginClick = { email, _ ->
                    playerName = email.substringBefore("@").ifBlank { "Jugador" }
                    goToLobby()
                },
                onGoogleClick = {
                    playerName = "Jugador"
                    goToLobby()
                },
                onGoToRegister = { navController.navigate(Routes.REGISTER) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen(
                onRegisterClick = { name, _, _, avatar ->
                    playerName = name
                    avatarIndex = avatar
                    goToLobby()
                },
                onGoToLogin = { navController.navigate(Routes.LOGIN) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.LOBBY) {
            LobbyScreen(
                playerName = playerName,
                avatarIndex = avatarIndex,
                onNewGame = { navController.navigate(Routes.NEW_GAME) },
                onJoinWithCode = { code ->
                    roomCode = code
                    navController.navigate(Routes.WAITING_ROOM)
                },
                onHistory = { navController.navigate(Routes.HISTORY) },
                onLogout = {
                    navController.navigate(Routes.START) {
                        popUpTo(Routes.LOBBY) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.NEW_GAME) {
            NewGameScreen(
                onCreate = { players, _ ->
                    maxPlayers = players
                    roomCode = "A7K3"
                    navController.navigate(Routes.WAITING_ROOM)
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.WAITING_ROOM) {
            WaitingRoomScreen(
                state = FakeScreenData.room(code = roomCode, maxPlayers = maxPlayers),
                onStart = { navController.navigate(Routes.GAME) },
                onCancel = backToLobby
            )
        }

        composable(Routes.GAME) {
            GameScreen(
                state = FakeData.gameState(),
                onTileSelected = {},
                onCellClick = { _, _ -> },
                onPlace = { navController.navigate(Routes.RESULT) },
                onDraw = {},
                onLeave = backToLobby
            )
        }

        composable(Routes.RESULT) {
            ResultScreen(
                result = FakeScreenData.result(),
                onPlayAgain = {
                    // Replace the finished game with a new one
                    navController.navigate(Routes.GAME) {
                        popUpTo(Routes.GAME) { inclusive = true }
                    }
                },
                onBackToMenu = backToLobby
            )
        }

        composable(Routes.HISTORY) {
            HistoryScreen(
                matches = FakeScreenData.history(),
                onBack = { navController.popBackStack() }
            )
        }
    }
}