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
    val scope = CoroutineScope(Job())
    scope.launch {
        try {
            thirdApd(this)
        } catch (e: Exception) {
            println("caught exception ${e.message}")
        }
    }.join()
}

private fun thirdApd(scope: CoroutineScope) {
    scope.launch {
        delay(100)
        throw  NullPointerException("null pointer exception")
    }
}