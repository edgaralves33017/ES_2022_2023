package com.example.es_tablewatcher.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Utilizador(
    val id: Int,
    val username: String?,
    val password: String?,
    val isAdmin: Boolean
) : Parcelable