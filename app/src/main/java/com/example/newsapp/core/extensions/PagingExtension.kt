package com.example.newsapp.core.extensions

import androidx.paging.LoadState
import com.example.newsapp.R
import retrofit2.HttpException
import java.io.IOException

val LoadState.Error.message: Int
    get() = when (error) {
        is HttpException -> {
            R.string.unexpected_error
        }

        is IOException -> {
            R.string.no_internet_error
        }

        else -> {
            R.string.unexpected_error
        }
    }
