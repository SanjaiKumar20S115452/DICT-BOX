package com.sanjai.dictbox.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DictionaryItem(
    @PrimaryKey
    val wordId: Int? = null,
    val meanings: List<Meaning>? = null,
//    val phonetics: List<Phonetic>,
//    val sourceUrls: List<String>,
    val word: String? = null
)