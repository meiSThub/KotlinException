package com.mei.ktexception.trycatch

import kotlinx.coroutines.*

/**
 * @date 2022/8/10
 * @author mxb
 * @desc
 * @desired
 */
fun main() = runBlocking<Unit> {
    val scope = CoroutineScope(Job())
    scope.launch {
        val num = doHardWork()
        println("计算结果：num=$num")
    }
    delay(2000)
}

private suspend fun doHardWork(): Int {
    delay(500) // 模拟耗时操作
    return try {
        1 / 0
    } catch (e: Exception) {
        println("try catch exception:$e")
        -1
    }
}