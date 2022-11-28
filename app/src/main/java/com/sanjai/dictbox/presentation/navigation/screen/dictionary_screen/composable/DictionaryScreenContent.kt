package com.sanjai.dictbox.presentation.navigation.screen.dictionary_screen.composable

import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ContentAlpha.disabled
import androidx.compose.material.ContentAlpha.high
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocalReadScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sanjai.dictbox.R
import com.sanjai.dictbox.data.model.Dictionary
import com.sanjai.dictbox.data.model.DictionaryItem
import com.sanjai.dictbox.presentation.navigation.screen.Screen
import com.sanjai.dictbox.presentation.navigation.screen.dictionary_screen.DictionaryScreenEvent
import com.sanjai.dictbox.ui.theme.*

@Composable
fun DictionaryScreenContent(
    dictionary: List<DictionaryItem>,
    typedWord: String,
    onEvent: (DictionaryScreenEvent) -> Unit,
    loadingState: Boolean,
    navController: NavHostController
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Black
    ) {
        Column(
            modifier = Modifier
                .padding(all = 15.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = color1,
                        fontSize = 25.sp
                    )
                    ) {
                        append("D")
                        withStyle(style = SpanStyle(
                            color = color2,
                            fontSize = 25.sp
                        )) {
                            append("I")
                            withStyle(style = SpanStyle(
                                color = color3,
                                fontSize = 25.sp
                            )) {
                                append("C")
                                withStyle(style = SpanStyle(
                                    color = color4,
                                    fontSize = 25.sp
                                )) {
                                    append("T  ")
                                    withStyle(style = SpanStyle(
                                        color = color5,
                                        fontSize = 25.sp
                                    )) {
                                        append("B")
                                        withStyle(style = SpanStyle(
                                            color = color1,
                                            fontSize = 25.sp
                                        )) {
                                            append("O")
                                            withStyle(style = SpanStyle(
                                                color = color2,
                                                fontSize = 25.sp
                                            )) {
                                                append("X")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                    contentDescription = null,
                    tint = Color.Red.copy(medium),
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screen.SavedScreen.route)
                        }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = typedWord, onValueChange = {
                onEvent(DictionaryScreenEvent.OnWordTyped(it))
            },
                label = {
                    Text(text = "Search Word", fontSize = 15.sp, color = Color.White.copy(disabled))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedIndicatorColor = Color.White.copy(disabled),
                    focusedIndicatorColor = Color.White.copy(disabled)
                ),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = null, tint = Color.White.copy(disabled))
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            if(dictionary.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "What's the meaning of legit ? \uD83E\uDD14", color = Color.LightGray.copy(
                            medium), fontFamily = FontFamily.SansSerif, fontSize = 12.sp)
                    }
                }
            }else {
                if(loadingState) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = Color.White
                        )
                    }
                }else {
                    LazyColumn {
                        items(
                            items = dictionary
                        ) { dict ->
                            DictionaryScreenContentItem(dict,onEvent)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun DictionaryScreenContentItem(
    dictionaryItem: DictionaryItem,
    onEvent: (DictionaryScreenEvent) -> Unit = {}
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
        color = Color(0xFF333533).copy(high)
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
                                dictionaryItem.word?.let { append(it) }
                            }
                        }
                    },
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                IconButton(onClick = {
                    onEvent(DictionaryScreenEvent.OnSaveWordClicked(dictionaryItem = dictionaryItem))
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                        contentDescription = null,
                        tint = Color.Red.copy(medium),
                        modifier = Modifier
                            .size(18.dp)
                    )
                }
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
                        dictionaryItem.meanings?.first()?.partOfSpeech?.let { append(it) }
                    }
                }
            }, fontWeight = FontWeight.Bold, color =Color.White)
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
                    )) {
                        dictionaryItem.meanings?.first()?.definitions?.first()
                            ?.let { append(it.definition) }
                    }
                }
            }, fontWeight = FontWeight.Bold,color =Color.White)
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
                    )) {
                        if(dictionaryItem.meanings?.first()?.synonyms?.isNotEmpty() == true) {
                            append(dictionaryItem.meanings.first().synonyms!!.joinToString(" , "))
                        }else {
                            append("Not available")
                        }
                    }
                }
            }, fontWeight = FontWeight.Bold,color =Color.White
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
                    )) {
                        if(dictionaryItem.meanings?.first()?.antonyms?.isNotEmpty() == true) {
                            append(dictionaryItem.meanings.first().antonyms!!.joinToString(" , "))
                        }else {
                            append("Not available")
                        }
                    }
                }
            }, fontWeight = FontWeight.Bold,color =Color.White
            )
        }
    }
}