package com.example.artur.kotlinpaging

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.PagedList
import com.example.artur.kotlinpaging.UserDB

/**
 * Created by Artur on 9/17/2017.
 */
class  UserViewModel(app: Application) : AndroidViewModel(app) {

    val userDao = UserDao.get(app).userDao()
    val allUsers = UserDao.getUsers()
            .create(0, PagedList.Config.Builder()
                    .setPageSize(15)
                    .setPrefetchDistance(5)
                    .setEnablePlaceholders(true).build())
}