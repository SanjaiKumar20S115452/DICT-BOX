package com.sanjai.dictbox.presentation.navigation.screen.saved_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjai.dictbox.data.model.SaveWord
import com.sanjai.dictbox.data.utils.Resource
import com.sanjai.dictbox.domain.repository.DictionaryLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedScreenViewModel @Inject constructor(
    private val repository: DictionaryLocalDataSource
 ) : ViewModel() {

     private var job: Job? = null

    init {
        job = viewModelScope.launch {
            delay(10L)
            getAllSavedWords()
        }
    }

    private val _allSavedWords = MutableStateFlow<List<SaveWord>>(emptyList())
    val allSavedWords: StateFlow<List<SaveWord>> = _allSavedWords

    private fun getAllSavedWords() {
        viewModelScope.launch {
            repository.getAllSavedWords().collectLatest { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let {
                            _allSavedWords.value = it
                        }
                    }
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                }
            }
        }
    }

 }