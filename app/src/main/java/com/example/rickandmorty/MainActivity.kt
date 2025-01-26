package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val characterViewModel: CharacterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                val navController = rememberNavController()
                AppNavigation(
                    navController = navController,
                    characterViewModel = characterViewModel
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        characterViewModel.fetchCharacters()
    }
}

@Composable
fun AppNavigation(navController: NavHostController, characterViewModel: CharacterViewModel) {
    NavHost(
        navController = navController,
        startDestination = "character_list"
    ) {
        composable("character_list") {
            CharacterListScreen(
                characterViewModel = characterViewModel,
                navController = navController
            )
        }
        composable(
            "character_detail/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId")
            if (characterId != null) {
                characterViewModel.fetchCharacterById(characterId)
            }

            val character by characterViewModel.selectedCharacter.collectAsState()
            if (character != null) {
                CharacterDetailScreen(character = character)
            } else {
                LoadingAnimation()
            }
        }
    }
}


