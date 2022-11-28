package com.sanjai.dictbox.data.repository

import com.sanjai.dictbox.data.api.DictionaryApi
import com.sanjai.dictbox.data.model.Dictionary
import com.sanjai.dictbox.data.utils.Resource
import com.sanjai.dictbox.domain.repository.DictionaryRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DictionaryRemoteDataSourceImpl(
    private val api: DictionaryApi
): DictionaryRemoteDataSource {

    override fun getSearchedDictionaryWord(word: String): Flow<Resource<Dictionary>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val searchedItems = api.getDictionaryWords(searchedWord = word).body()
            if (searchedItems != null) {
                if(searchedItems.isNotEmpty()) {
                    emit(Resource.Success(data = searchedItems))
                    emit(Resource.Loading(isLoading = false))
                }
            }
            emit(Resource.Loading(isLoading = false))
        }
    }

}