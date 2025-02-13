package com.example.rickandmorty.presentation.characters.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rickandmorty.presentation.characters.viewmodel.CharacterViewModel
import kotlinx.coroutines.launch

@Composable
fun CharacterListScreen(characterViewModel: CharacterViewModel, navController: NavController) {
    val characters by characterViewModel.characters.collectAsState()
    val loading by characterViewModel.loading.collectAsState()
    val hasMorePages by characterViewModel.hasMorePages.collectAsState()

    val gridState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    val isAtEnd by remember {
        derivedStateOf {
            val lastVisibleItem = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleItem >= (characters.size - 1)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF39FF14))
                .padding(vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Rick and Morty",
                style = MaterialTheme.typography.titleLarge.copy(color = Color.White),
                textAlign = TextAlign.Center
            )
        }
        Box(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
            if (loading && characters.isEmpty()) {
                LottieLoadingAnimation()
            } else {
                LazyVerticalGrid(
                    state = gridState,
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    items(characters) { character ->
                        CharacterCard(character) {
                            navController.navigate("character_detail/${character.id}")
                        }
                    }

                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            DynamicButton(
                                text = if (!hasMorePages && isAtEnd) "Voltar ao topo" else "Carregar mais",
                                onClick = {
                                    if (!hasMorePages && isAtEnd) {
                                        coroutineScope.launch {
                                            gridState.animateScrollToItem(0)
                                        }
                                    } else {
                                        characterViewModel.loadNextPage()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
