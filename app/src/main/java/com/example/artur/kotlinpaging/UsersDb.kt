package com.example.artur.kotlinpaging

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by Artur on 9/18/2017.
 */


@Database(entities = arrayOf(User::class), version = 1)
abstract class UsersDb: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: UsersDb? = null

        @Synchronized
        fun get(context: Context): UsersDb{
            if (instance == null){
                instance = Room.databaseBuilder(context.applicationContext, UsersDb::class.java,
                        "users.db").allowMainThreadQueries().addCallback(object: RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        get(context.applicationContext).userDao().insert(USERS.map { User(id=0, name=it) })
                    }
                }).build()
            }
            return instance!!
        }


    }
}

private val USERS = arrayListOf(
        "Vincent","Valentine","Yeukai","Isheunopa","Kudakwashe","Sharon","Roseline","Muslin",
        "Ropafadzo","Tariro","Matipaishe","Makomborero","Anesuishe","Ationaishe","Tafadzwa",
        "Tadiwanashe","Akarurama","Tinodaishe","Taponeswa","Takundiswa","Anotidaishe","Aviella",
        "Akudzweishe","Anashe","Sibusisiwe","Thando","Munenyasha","Kutenda","Koshesai","Brando"
)