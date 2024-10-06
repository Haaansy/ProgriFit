package com.cc17.progrifit30.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cc17.progrifit30.db.model.User
import com.cc17.progrifit30.db.userDB.UserDao
import com.cc17.progrifit30.db.model.UserRoutes
import com.cc17.progrifit30.db.userDB.UserRoutesDao

@Database(
    entities = [User::class, UserRoutes::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userdao(): UserDao
    abstract fun userRoutesdao(): UserRoutesDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "Main-Database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}