package com.example.es_tablewatcher.data.model

import android.os.Parcel
import android.os.Parcelable

data class Utilizador(
    val id: Int,
    val username: String?,
    val password: String?,
    val isAdmin: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeByte(if (isAdmin) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Utilizador> {
        override fun createFromParcel(parcel: Parcel): Utilizador {
            return Utilizador(parcel)
        }

        override fun newArray(size: Int): Array<Utilizador?> {
            return arrayOfNulls(size)
        }
    }
}