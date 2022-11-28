package com.sanjai.dictbox.presentation.navigation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sanjai.dictbox.presentation.navigation.screen.dictionary_screen.composable.DictionaryScreen
import com.sanjai.dictbox.presentation.navigation.screen.saved_screen.composable.SavedScreen

@Composable
fun SetUpNavigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.DictionaryScreen.route) {
        composable(
            route = Screen.DictionaryScreen.route
        ) {
            DictionaryScreen(navController = navController)
        }
        composable(
            route = Screen.SavedScreen.route
        ) {
            SavedScreen()
        }
    }
}