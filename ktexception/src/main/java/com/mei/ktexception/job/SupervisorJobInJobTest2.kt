package com.mei.ktexception.job

import kotlinx.coroutines.*

/**
 * @date 2022/8/10
 * @author mxb
 * @desc
 * @desired
 */
fun main() {
    val scope = CoroutineScope(Job())

    scope.launch {
        println("job1")
        try {
            delay(100)
            println("job1,延时完成")
        } catch (e: Exception) {
            println("job1 is isCanceled:${e is CancellationException}")
        }
    }
    val supervisorJob = SupervisorJob()
    scope.launch(supervisorJob) {
        "job2"
        println("job2")
        launch {
            println("job5，开始延时")
            try {
                delay(100)
                println("job5,延时完成")
            } catch (e: Exception) {
                println("job5 is isCanceled:${e is CancellationException}")
            }
        }
        launch {
            println("job6，抛出异常")
            val num = 1 / 0
        }
        try {
            delay(50)
        } catch (e: Exception) {
            println("job2 is isCanceled:${e is CancellationException}")
        }
    }
    scope.launch(supervisorJob) {
        println("job4")
        try {
            delay(100)
            println("job4,延时完成")
        } catch (e: Exception) {
            println("job4 is isCanceled:${e is CancellationException}")
        }
    }
    scope.launch {
        println("job3")
        try {
            delay(100)
            println("job3,延时完成")
        } catch (e: Exception) {
            println("job3 is isCanceled:${e is CancellationException}")
        }
    }
    Thread.sleep(2000)
}