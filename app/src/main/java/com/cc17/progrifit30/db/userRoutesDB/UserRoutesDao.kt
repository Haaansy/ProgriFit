package com.cc17.progrifit30.db.userDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cc17.progrifit30.db.model.UserRoutes

@Dao
interface UserRoutesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserRoutes(userRoutes: UserRoutes)

    @Update
    suspend fun updateUserRoutes(userRoutes: UserRoutes)

    @Query("SELECT * FROM `User-Routes` ORDER BY id ASC")
    fun getUserRoutesDetails(): LiveData<List<UserRoutes>>

    @Delete
    suspend fun deleteUserRoutes(userRoutes: UserRoutes)

}