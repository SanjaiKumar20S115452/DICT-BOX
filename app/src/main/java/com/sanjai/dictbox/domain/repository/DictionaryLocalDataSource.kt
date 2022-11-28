package com.sanjai.dictbox.domain.repository

import com.sanjai.dictbox.data.model.DictionaryItem
import com.sanjai.dictbox.data.model.SaveWord
import com.sanjai.dictbox.data.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DictionaryLocalDataSource {
    fun getAllSavedWords(): Flow<Resource<List<SaveWord>>>
    suspend fun saveWord(dictionaryItem: DictionaryItem, onWordSaved: (Boolean) -> Unit)
}