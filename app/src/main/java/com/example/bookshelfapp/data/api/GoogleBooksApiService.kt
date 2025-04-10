package com.example.bookshelfapp.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApiService {
    @GET("volumes")
    suspend fun getNewestBooks(
        @Query("q") query: String = "android development",
        @Query("maxResults") maxResults: Int = 20
    ): BooksResponse

    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 20
    ): BooksResponse
}

