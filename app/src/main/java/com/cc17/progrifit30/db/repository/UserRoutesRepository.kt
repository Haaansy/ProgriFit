package com.cc17.progrifit30.db.repository

import androidx.lifecycle.LiveData
import com.cc17.progrifit30.db.model.User
import com.cc17.progrifit30.db.model.UserRoutes
import com.cc17.progrifit30.db.userDB.UserRoutesDao

class UserRoutesRepository(private val userRoutesDao: UserRoutesDao) {

    val readAllData: LiveData<List<UserRoutes>> = userRoutesDao.getUserRoutesDetails()

    suspend fun insertUserRoutes(userRoutes: UserRoutes){
        userRoutesDao.insertUserRoutes(userRoutes)
    }

    suspend fun updateUserRoutes(userRoutes: UserRoutes) {
        userRoutesDao.updateUserRoutes(userRoutes)
    }

    suspend fun deleteUserRoutes(userRoutes: UserRoutes) {
        userRoutesDao.deleteUserRoutes(userRoutes)
    }

}