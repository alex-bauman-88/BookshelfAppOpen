package com.example.bookshelfapp.data.repository

import com.example.bookshelfapp.data.api.GoogleBooksApiService
import com.example.bookshelfapp.domain.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val booksApiService: GoogleBooksApiService
) : BookRepository {

    // search with query
    override fun searchBooks(query: String): Flow<List<Book>> = flow {
        try {
            val response = booksApiService.searchBooks(query)
            val books = response.items.map { bookItem ->
                Book(
                    id = bookItem.id,
                    title = bookItem.volumeInfo.title,
                    authors = bookItem.volumeInfo.authors ?: listOf("Unknown author"), // TODO: Context
                    thumbnailUrl = bookItem.volumeInfo.imageLinks?.thumbnail?.replace(
                        "http:",// TODO: Context
                        "https:"// TODO: Context
                    )
                )
            }
            emit(books)
        } catch (e: Exception) {
            // TODO: handle errors
            emit(emptyList())
        }
    }

    override fun getNewestBooks(): Flow<List<Book>> = flow {
        try {
            val response = booksApiService.getNewestBooks()
            val books = response.items.map { bookItem ->
                Book(
                    id = bookItem.id,
                    title = bookItem.volumeInfo.title,
                    authors = bookItem.volumeInfo.authors ?: listOf("Unknown author"), // TODO: Context
                    thumbnailUrl = bookItem.volumeInfo.imageLinks?.thumbnail?.replace(
                        "http:", // TODO: Context
                        "https:"// TODO: Context
                    )
                )
            }
            emit(books)
        } catch (e: Exception) {
            // TODO: handle errors
            emit(emptyList())
        }
    }
}