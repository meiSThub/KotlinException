package com.mei.ktexception.supervisorjob

import kotlinx.coroutines.*

/**
 * @date 2022/8/10
 * @author mxb
 * @desc
 * @desired
 */
fun main() {
    val scope = CoroutineScope(SupervisorJob())
    scope.launch {
        println("job1")
        launch {
            println("job2")
            try {
                delay(100)
            } catch (e: Exception) {
                println("job2 is isCanceled:${e is CancellationException}")
            }
        }

        launch {
            println("job3,抛出异常")
            val num = 1 / 0
        }
        try {
            delay(50)
            println("job1,延时完成")
        } catch (e: Exception) {
            println("job1 is isCanceled:${e is CancellationException}")
        }
    }

    Thread.sleep(2000)
}