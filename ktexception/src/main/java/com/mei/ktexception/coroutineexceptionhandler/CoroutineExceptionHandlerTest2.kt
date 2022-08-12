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
        println("outer handler异常：$throwable")
    }

    val scope = CoroutineScope(Job())

    scope.launch(handler) {
        // 只要启动的协程的父Job，就是普通的Job对象，则给它指定CoroutineExceptionHandler，就可以捕获异常
        launch(Job() + CoroutineExceptionHandler { _, throwable ->
            println("inner handler:$throwable")
        }) {
            1 / 0
        }
    }

    Thread.sleep(2000)
}