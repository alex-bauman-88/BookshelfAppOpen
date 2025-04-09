package com.example.bookshelfapp.data.repository

import com.example.bookshelfapp.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun searchBooks(query: String): Flow<List<Book>>
    fun getNewestBooks(): Flow<List<Book>>
}
