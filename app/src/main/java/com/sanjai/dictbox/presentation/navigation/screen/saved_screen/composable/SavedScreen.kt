package com.sanjai.dictbox.presentation.navigation.screen.saved_screen.composable

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.sanjai.dictbox.presentation.navigation.screen.saved_screen.SavedScreenViewModel

@Composable
fun SavedScreen(
    viewModel: SavedScreenViewModel = hiltViewModel()
) {
    val allSavedWords by viewModel.allSavedWords.collectAsState()
    Scaffold {
        SavedScreenContent(item = allSavedWords)
    }
}