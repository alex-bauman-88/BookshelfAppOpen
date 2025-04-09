package com.example.bookshelfapp.domain.model

data class Book(
    val id: String,
    val title: String,
    val authors: List<String>,
    val thumbnailUrl: String?
    )

