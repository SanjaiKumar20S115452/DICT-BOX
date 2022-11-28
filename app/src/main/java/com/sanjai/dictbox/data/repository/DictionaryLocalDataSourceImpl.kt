package com.sanjai.dictbox.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.sanjai.dictbox.data.model.DictionaryItem
import com.sanjai.dictbox.data.model.SaveWord
import com.sanjai.dictbox.data.utils.Constants
import com.sanjai.dictbox.data.utils.Resource
import com.sanjai.dictbox.domain.repository.DictionaryLocalDataSource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

class DictionaryLocalDataSourceImpl(
    private val firebaseFireStore: FirebaseFirestore
): DictionaryLocalDataSource {

    private val savedRef: CollectionReference = firebaseFireStore.collection(Constants.SAVED_WORDS)

    override fun getAllSavedWords(): Flow<Resource<List<SaveWord>>> {
        return callbackFlow {
            var snapShotListener: ListenerRegistration? = null
            try {
                snapShotListener = savedRef.addSnapshotListener { snapShot, e ->
                    val response = if(snapShot != null) {
                        val allSavedNotes = snapShot.toObjects(SaveWord::class.java)
                        Resource.Success(allSavedNotes)
                    }else {
                        Resource.Error(message = e?.localizedMessage ?: "Unknown error occurred")
                    }
                    trySend(response)
                }
            }catch (e: Exception) {
                trySend(Resource.Error(message = e.localizedMessage ?: ""))
            }
            awaitClose {
                snapShotListener?.remove()
            }
        }
    }

    override suspend fun saveWord(dictionaryItem: DictionaryItem, onWordSaved: (Boolean) -> Unit) {
        val documentId = savedRef.document().id
        val saveWord = SaveWord(wordId = documentId,dictionaryItem = dictionaryItem)
        savedRef.document(documentId)
            .set(saveWord)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    onWordSaved(true)
                }else {
                    onWordSaved(false)
                }
            }
    }
}