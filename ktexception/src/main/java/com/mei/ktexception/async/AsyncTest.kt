package com.mei.ktexception.async

import kotlinx.coroutines.*
import java.lang.AssertionError
import java.lang.Exception
import kotlin.coroutines.EmptyCoroutineContext

/**
 * @date 2022/8/10
 * @author mxb
 * @desc
 * @desired
 */
fun main() = runBlocking<Unit> {
    val scope = CoroutineScope(Job())
    val result = scope.async {
        println("Throwing exception from async")
        throw AssertionError("test")
    }
    try {
        result.await()
    } catch (e: Throwable) {
        println("try catch 异常:$e")
    }
}