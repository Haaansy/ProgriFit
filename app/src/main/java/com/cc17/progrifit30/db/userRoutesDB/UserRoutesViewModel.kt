package com.cc17.progrifit30.db.userDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cc17.progrifit30.db.UserDatabase
import com.cc17.progrifit30.db.repository.UserRoutesRepository
import com.cc17.progrifit30.db.model.UserRoutes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRoutesViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<UserRoutes>>
    private val repository: UserRoutesRepository

    init {
        val userRoutesDao = UserDatabase.getDatabase(application).userRoutesdao()
        repository = UserRoutesRepository(userRoutesDao)
        readAllData = repository.readAllData
    }

    fun insertUserRoutes(userRoutes: UserRoutes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUserRoutes(userRoutes)
        }
    }

    fun updateUserRoutes(userRoutes: UserRoutes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserRoutes(userRoutes)
        }
    }

    fun deleteUserRoutes(userRoutes: UserRoutes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUserRoutes(userRoutes)
        }
    }
}