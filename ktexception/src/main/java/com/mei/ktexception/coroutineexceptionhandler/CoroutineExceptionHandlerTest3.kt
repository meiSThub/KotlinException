package com.mei.ktexception.coroutineexceptionhandler

import kotlinx.coroutines.*

/**
 * @date 2022/8/10
 * @author mxb
 * @desc
 * @desired
 */

fun main() {
    val handler = CoroutineExceptionHandler { _, throwable ->
        println("handler 捕获异常：$throwable")
    }
    val scope = CoroutineScope(Job())
    scope.launch(handler) {
        launch {
            println("开始执行耗时操作")
            val num = doHardWork()
            println("num=$num")
        }
    }
    Thread.sleep(2000)
}

private suspend fun doHardWork(): Int {
    delay(500) // 模拟耗时操作
    return 1 / 0
}