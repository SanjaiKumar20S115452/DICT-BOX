package com.sanjai.dictbox.presentation.navigation.screen.dictionary_screen

import com.sanjai.dictbox.data.model.DictionaryItem

sealed class DictionaryScreenEvent {
    data class OnWordTyped(val word: String): DictionaryScreenEvent()
    data class OnSaveWordClicked(val dictionaryItem: DictionaryItem): DictionaryScreenEvent()
}
