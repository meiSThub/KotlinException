package com.mei.ktexception.job

import kotlinx.coroutines.*
import java.lang.NullPointerException

/**
 * @date 2022/8/11
 * @author mxb
 * @desc
 * @desired
 */
fun main() = runBlocking {
    // testJob()
    testSupervisorJob()
}

private suspend fun testJob() {
    val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("caught exception in handler $throwable ")
    }
    val scope = CoroutineScope(Job() + handler)
    scope.launch {
        delay(100)
        println("job1,抛异常")
        throw NullPointerException()
    }.join()
    scope.launch {
        delay(200)
        println("job2")
    }.join()
    scope.launch {
        delay(300)
        println("job3")
    }.join()
}

private suspend fun testSupervisorJob(){
    val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("caught exception in handler $throwable ")
    }
    val scope = CoroutineScope(SupervisorJob() + handler)
    scope.launch {
        delay(100)
        println("job1,抛异常")
        throw NullPointerException()
    }.join()
    scope.launch {
        delay(200)
        println("job2")
    }.join()
    scope.launch {
        delay(300)
        println("job3")
    }.join()
}