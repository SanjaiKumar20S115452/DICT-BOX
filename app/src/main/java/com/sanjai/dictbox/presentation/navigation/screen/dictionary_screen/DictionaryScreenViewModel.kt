package com.sanjai.dictbox.presentation.navigation.screen.dictionary_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjai.dictbox.data.model.Dictionary
import com.sanjai.dictbox.data.model.DictionaryItem
import com.sanjai.dictbox.data.utils.Resource
import com.sanjai.dictbox.data.utils.UiEvent
import com.sanjai.dictbox.domain.repository.DictionaryLocalDataSource
import com.sanjai.dictbox.domain.repository.DictionaryRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.processNextEventInCurrentThread
import javax.inject.Inject

@HiltViewModel
class DictionaryScreenViewModel @Inject constructor(
    private val repository: DictionaryRemoteDataSource,
    private val repository2: DictionaryLocalDataSource
 ) : ViewModel() {

    private var searchJob: Job? = null

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _typedWord = mutableStateOf("")
    val typedWord: State<String> = _typedWord

    private val _searchedWord = MutableStateFlow<List<DictionaryItem>>(emptyList())
    val searchedWord: StateFlow<List<DictionaryItem>> = _searchedWord

    fun onEvent(event: DictionaryScreenEvent) {
        when(event) {
            is DictionaryScreenEvent.OnWordTyped -> {
                getSearchedWord(event.word)
            }
            is DictionaryScreenEvent.OnSaveWordClicked -> {
                viewModelScope.launch {
                    repository2.saveWord(dictionaryItem = event.dictionaryItem, onWordSaved = {
                        if(it) {
                            sendUiEvent(UiEvent.ShowToast(message = "Word snatched!"))
                        }else {
                            sendUiEvent(UiEvent.ShowToast("Unable to save at the moment!"))
                        }
                    })
                }
            }
        }
    }

    var screenState by mutableStateOf(ScreenState())

     private fun getSearchedWord(query: String) {
         _typedWord.value = query
         searchJob?.cancel()
         searchJob = viewModelScope.launch {
             if(_typedWord.value.isEmpty()) {
                 sendUiEvent(UiEvent.ShowToast(message = "Please enter a word to surf!"))
                 return@launch
             }
             delay(200L)
             repository.getSearchedDictionaryWord(_typedWord.value).collectLatest { result ->
                 when(result) {
                     is Resource.Success -> {
                         result.data?.let {
                             _searchedWord.value = it
                         }
                     }
                     is Resource.Error -> {}
                     is Resource.Loading -> {
                         screenState = screenState.copy(
                             isLoading = result.isLoading
                         )
                     }
                 }
             }
         }
     }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
 }