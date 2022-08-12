package com.mei.ktexception.async

import kotlinx.coroutines.*
import java.lang.AssertionError
import kotlin.coroutines.EmptyCoroutineContext

/**
 * @date 2022/8/10
 * @author mxb
 * @desc
 * @desired
 */
fun main() = runBlocking<Unit> {
    val scope = CoroutineScope(Job())
    scope.launch {
        val result = async {
            println("Throwing exception from async")
            delay(100)
            println("抛异常")
            throw AssertionError("test")
        }
        delay(200)
        try {
            result.await()
        } catch (e: Throwable) {
            println("try catch 异常:$e")
        }
        println("代码结束")
    }.join()
}