package com.example.artur.kotlinpaging

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.artur.kotlinpaging.ioThread



/**
 * Created by Artur on 9/17/2017.
 */
@Database(entities = arrayOf(User::class), version = 1)

abstract class UserDB: RoomDatabase() {
    abstract fun UserDao(): UserDao

    companion object {
        private var instance: UserDB? = null

        @Synchronized
        fun get(context: Context): UserDB {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, UserDB::class.java, "users.db").addCallback(object : RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                            fillInDb(context)
                        }
                }).build()
            }
        }

        private fun fillInDb(context: Context): Any {
            ioThread {
                get(context.applicationContext).userDao().insert(USERS.map { User(id=0, name=it) })
            }
        }
    }

}