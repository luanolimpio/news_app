package com.example.newsapp.data.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class SourceDto(
    val id: String?,
    val name: String
) : Parcelable