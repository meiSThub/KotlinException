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
    val outerHandler = CoroutineExceptionHandler { context, throwable ->
        println("caught exception in outer handler ${throwable.message}")
    }
    val innerHandler = CoroutineExceptionHandler { context, throwable ->
        println("caught exception in inner handler ${throwable.message}")
    }
    val scope = CoroutineScope(Job())
    scope.launch(outerHandler) {
        supervisorScope {
            launch(innerHandler) {
                delay(100)
                throw  NullPointerException("null pointer exception")
            }
        }
    }.join()
}