package com.sanjai.dictbox.presentation.navigation.screen.dictionary_screen.composable

import android.util.Log
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sanjai.dictbox.data.utils.UiEvent
import com.sanjai.dictbox.presentation.navigation.screen.dictionary_screen.DictionaryScreenEvent
import com.sanjai.dictbox.presentation.navigation.screen.dictionary_screen.DictionaryScreenViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DictionaryScreen(
    viewModel: DictionaryScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val context = LocalContext.current
    val dictionary by viewModel.searchedWord.collectAsState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when(event) {
                is UiEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    Log.i("TAG", dictionary.toString())
    val typedWord = viewModel.typedWord.value
    val screenState = viewModel.screenState.isLoading
    val error = viewModel.screenState.error != ""
    Scaffold {
        DictionaryScreenContent(
            dictionary = dictionary,
            typedWord = typedWord,
            onEvent = viewModel::onEvent,
            loadingState = screenState,
            navController = navController
        )
    }
}