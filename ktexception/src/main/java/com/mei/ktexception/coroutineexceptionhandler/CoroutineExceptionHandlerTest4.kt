package com.mei.ktexception.coroutineexceptionhandler

import kotlinx.coroutines.*
import java.lang.NullPointerException

/**
 * @date 2022/8/10
 * @author mxb
 * @desc
 * @desired
 */

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, throwable ->
        println("handler 捕获异常：$throwable")
    }
    val scope = CoroutineScope(Job())
    scope.launch {
        launch(handler) {
            println("开始执行耗时操作")
            throw NullPointerException()
        }
    }.join()
}