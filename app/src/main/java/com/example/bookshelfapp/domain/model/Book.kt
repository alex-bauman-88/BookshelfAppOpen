package com.example.bookshelfapp.domain.model

// Domain Model (for UI layer)
data class Book(
    val id: String,
    val title: String,
    val authors: List<String>,
    val thumbnailUrl: String?
    )

