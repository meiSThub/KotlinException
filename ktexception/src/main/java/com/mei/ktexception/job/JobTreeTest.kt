package com.mei.ktexception.job

import kotlinx.coroutines.*

/**
 * @date 2022/8/10
 * @author mxb
 * @desc
 * @desired
 */
fun main() = runBlocking {
    val jobRoot = Job()
    val scope = CoroutineScope(jobRoot)
    scope.launch {
        println("job1")
    }.join()
    scope.launch {
        println("job2")
        launch {
            println("job4")
        }
        launch {
            println("job5")
        }
    }.join()
    scope.launch {
        println("job3")
    }.join()
}