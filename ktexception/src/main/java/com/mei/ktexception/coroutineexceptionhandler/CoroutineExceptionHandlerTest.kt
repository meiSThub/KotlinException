package com.mei.ktexception.coroutineexceptionhandler

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

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

    val scope = CoroutineScope(Job())

    scope.launch(handler) {
        println("curr handler=${coroutineContext[CoroutineExceptionHandler]}")
        println("scope handler=${scope.coroutineContext[CoroutineExceptionHandler]}")
    }

    scope.launch {
        println("抛出异常")
        1 / 0
    }

    Thread.sleep(2000)
}