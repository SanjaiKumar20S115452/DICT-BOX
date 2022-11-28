package com.sanjai.dictbox.presentation.navigation.screen.saved_screen.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanjai.dictbox.R
import com.sanjai.dictbox.data.model.DictionaryItem
import com.sanjai.dictbox.data.model.SaveWord
import com.sanjai.dictbox.presentation.navigation.screen.dictionary_screen.DictionaryScreenEvent
import com.sanjai.dictbox.presentation.navigation.screen.dictionary_screen.composable.DictionaryScreenContentItem

@Composable
fun SavedScreenContent(
    item: List<SaveWord>
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(
                items = item
            ) { dict ->
                SavedScreenContentItem(saveWord = dict)
            }
        }
    }
}

@Composable
fun SavedScreenContentItem(
    saveWord: SaveWord
) {
    Surface(
        modifier = Modifier
            .padding(all = 5.dp)
            .fillMaxWidth(),
        border = BorderStroke(
            width = 1.dp,
            color = Color.Black.copy(0.2f)
        ),
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFF333533).copy(ContentAlpha.high)
    ) {
        Column(
            modifier = Modifier
                .padding(all = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            fontSize = 12.sp,
                            color = Color(0xFFff99c8)
                        )
                        ) {
                            append("WORD: ")
                            withStyle(style = SpanStyle(
                                fontSize = 14.sp,
                                color = Color.White
                            )
                            ) {
                                saveWord.dictionaryItem?.let { it.word?.let { it1 -> append(it1) } }
                            }
                        }
                    },
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    fontSize = 12.sp,
                    color = Color(0xFFfcf6bd)
                )
                ) {
                    append("PARTS OF SPEECH: ")
                    withStyle(style = SpanStyle(
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    ) {
                        saveWord.dictionaryItem?.meanings?.first()?.let { it.partOfSpeech?.let { it1 ->
                            append(
                                it1
                            )
                        } }
                    }
                }
            }, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(7.dp))
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    fontSize = 12.sp,
                    color = Color(0xFFd0f4de)
                )
                ) {
                    append("DEFINITIONS:  ")
                    withStyle(style = SpanStyle(
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    ) {
                        saveWord.dictionaryItem?.meanings?.first()?.definitions?.first()
                            ?.let { append(it.definition) }
                    }
                }
            }, fontWeight = FontWeight.Bold,color = Color.White)
            Spacer(modifier = Modifier.height(7.dp))
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    fontSize = 12.sp,
                    color = Color(0xFFd0f4de)
                )
                ) {
                    append("SYNONYMS:  ")
                    withStyle(style = SpanStyle(
                        fontSize = 12.sp,
                        color = Color.White
                    )
                    ) {
                        if(saveWord.dictionaryItem?.meanings?.first()?.synonyms?.isNotEmpty() == true) {
                            append(saveWord.dictionaryItem.meanings.first().synonyms!!.joinToString(" , "))
                        }else {
                            append("Not available")
                        }
                    }
                }
            }, fontWeight = FontWeight.Bold,color = Color.White
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    fontSize = 12.sp,
                    color = Color(0xFFd0f4de)
                )
                ) {
                    append("ANTONYMS:  ")
                    withStyle(style = SpanStyle(
                        fontSize = 12.sp,
                        color = Color.White
                    )
                    ) {
                        if(saveWord.dictionaryItem?.meanings?.first()?.antonyms?.isNotEmpty() == true) {
                            append(saveWord.dictionaryItem.meanings.first().antonyms!!.joinToString(" , "))
                        }else {
                            append("Not available")
                        }
                    }
                }
            }, fontWeight = FontWeight.Bold,color = Color.White
            )
        }
    }
}