package com.example.newsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.core.Constants

@Entity(tableName = Constants.ARTICLE_TABLE)
data class ArticleEntity(
    val sourceName: String,
    val author: String?,
    val title: String,
    val description: String?,
    @PrimaryKey val url: String,
    val urlToImage: String?,
    val publishedAt: String,
)
