package com.example.newsapp.core.extensions

import com.example.newsapp.data.local.ArticleEntity
import com.example.newsapp.data.remote.ArticleDto
import com.example.newsapp.domain.models.Article
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

val Article.encode: Article
    get() {
        return copy(
            url = encodeUrl(url),
            urlToImage = urlToImage?.let { encodeUrl(it) }
        )
    }

val Article.decode: Article
    get() {
        return copy(
            url = decodeUrl(url),
            urlToImage = urlToImage?.let { decodeUrl(it) }
        )
    }

val ArticleDto.toArticle: Article
    get() {
        return Article(
            sourceName = source.name,
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt
        )
    }

val ArticleEntity.toArticle: Article
    get() {
        return Article(
            sourceName = sourceName,
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt
        )
    }

val Article.toArticleEntity: ArticleEntity
    get() {
        return ArticleEntity(
            sourceName = sourceName,
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt
        )
    }

private fun encodeUrl(url: String): String {
    return URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
}

private fun decodeUrl(encodedUrl: String): String {
    return URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
}