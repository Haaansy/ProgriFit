package com.cc17.progrifit30.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "User-Routes")
data class UserRoutes (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val points: String
): Parcelable