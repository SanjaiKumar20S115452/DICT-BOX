package com.sanjai.dictbox.data.api

import com.sanjai.dictbox.data.model.Dictionary
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DictionaryApi {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getDictionaryWords(
        @Path("word")
        searchedWord: String
    ): Response<Dictionary>

}