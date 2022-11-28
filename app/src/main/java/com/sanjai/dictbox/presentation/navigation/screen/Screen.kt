package com.sanjai.dictbox.presentation.navigation.screen

sealed class Screen(val route: String) {
    object DictionaryScreen: Screen("dictionary_screen")
    object SavedScreen: Screen("saved_screen")
}
