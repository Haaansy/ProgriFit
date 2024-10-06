package com.cc17.progrifit30.db.repository

import androidx.lifecycle.LiveData
import com.cc17.progrifit30.db.userDB.UserDao
import com.cc17.progrifit30.db.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<User> = userDao.getUserDetails()

    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }

}