package com.sanjai.dictbox.domain.repository

import com.sanjai.dictbox.data.model.Dictionary
import com.sanjai.dictbox.data.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DictionaryRemoteDataSource {
    fun getSearchedDictionaryWord(word: String): Flow<Resource<Dictionary>>
}