package com.example.es_tablewatcher.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Prato(
    val id : Int,
    val descricao: String,
    val preco: Double
) : Parcelable