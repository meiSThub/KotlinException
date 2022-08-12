package com.mei.ktexception.supervisorjob

import kotlinx.coroutines.*

/**
 * @date 2022/8/10
 * @author mxb
 * @desc
 * @desired
 */
fun main() {
    val handler = CoroutineExceptionHandler { _, throwable ->
        println("异常：$throwable")
    }
    val scope = CoroutineScope(SupervisorJob() + handler)
    scope.launch {
        println("job1 start")
        delay(2000)
        println("job1 end")
    }
    scope.launch {
        println("job2 start")
        delay(1000)
        1 / 0
        println("job2 end")
    }
    scope.launch {
        println("job3 start")
        delay(2000)
        println("job4 end")
    }

    Thread.sleep(4000)
}