package com.example.artur.kotlinpaging

import java.util.concurrent.Executors

/**
 * Created by Artur on 9/17/2017.
 */


private val IO_THREAD = Executors.newSingleThreadExecutor()

fun inThread(func: () ->Unit) {
    IO_THREAD.execute(func)
}