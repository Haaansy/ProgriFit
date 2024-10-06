package com.cc17.progrifit30.db.userDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cc17.progrifit30.db.UserDatabase
import com.cc17.progrifit30.db.model.User
import com.cc17.progrifit30.db.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<User>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userdao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(user)
        }
    }
}