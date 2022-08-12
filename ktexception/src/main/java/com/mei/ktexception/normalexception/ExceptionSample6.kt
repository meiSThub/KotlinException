package com.mei.ktexception.normalexception

import kotlinx.coroutines.*
import java.lang.NullPointerException

/**
 * @date 2022/8/10
 * @author mxb
 * @desc
 * @desired
 */

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { context, throwable ->
        println("caught exception in handler ${throwable.message}")
    }
    val scope = CoroutineScope(Job())
    scope.launch {
        try {
            val scope= CoroutineScope(Job())
            scope.dowork()
            // coroutineScope {
                launch(handler) {
                    delay(100)
                    throw  NullPointerException("null pointer exception")
                }
            // }
        } catch (e: Exception) {
            println("caught exception in try catch exception ${e.message}")
        }

    }.join()
}

suspend  fun  CoroutineScope.dowork(){
    launch {
        delay(100)
        throw  NullPointerException("null pointer exception")
    }
}