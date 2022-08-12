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
        println("caught exception ${throwable.message}")
    }
    val scope = CoroutineScope(Job())
    scope.launch {
        launch(handler) {
            delay(100)
            throw  NullPointerException("null pointer exception")
        }
    }.join()
}