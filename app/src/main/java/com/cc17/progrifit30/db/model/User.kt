package com.cc17.progrifit30.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User-Table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val age: Int,
    val weight: Double,
    val tdee: Double,
    val duration: Int
)