package com.example.bookshelfapp.ui.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelfapp.data.repository.BookRepository
import com.example.bookshelfapp.domain.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _searchResults = MutableStateFlow<List<Book>>(emptyList())
    val searchResults: StateFlow<List<Book>> = _searchResults

    private val _newestBooks = MutableStateFlow<List<Book>>(emptyList())
    val newestBooks: StateFlow<List<Book>> = _newestBooks

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun searchBooks(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            bookRepository.searchBooks(query)
                .catch { e ->
                    _errorMessage.value = e.message
                    _isLoading.value = false
                }
                .collect { books ->
                    _searchResults.value = books
                    _isLoading.value = false
                }
        }
    }

    fun loadNewestBooks() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            bookRepository.getNewestBooks()
                .catch { e ->
                    _errorMessage.value = e.message
                    _isLoading.value = false
                }
                .collect { books ->
                    _newestBooks.value = books
                    _isLoading.value = false
                }
        }
    }
}