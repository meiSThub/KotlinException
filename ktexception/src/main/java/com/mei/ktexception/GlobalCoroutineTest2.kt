package com.mei.ktexception

import kotlinx.coroutines.*
import java.lang.NullPointerException

/**
 * @date 2022/8/8
 * @author mxb
 * @desc
 * @desired
 */
fun main() = runBlocking<Unit> {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d("exceptionHandler", "${coroutineContext[CoroutineName].toString()} 处理异常 ：$throwable")
    }
    GlobalScope.launch(exceptionHandler) {
        supervisorScope {
            launch(CoroutineName("异常子协程")) {
                Log.d("${Thread.currentThread().name}", "我要开始抛异常了")
                throw NullPointerException("空指针异常")
            }
            for (index in 0..10) {
                launch(CoroutineName("子协程$index")) {
                    Log.d("${Thread.currentThread().name}正常执行", "$index")
                    if (index % 3 == 0) {
                        throw NullPointerException("子协程${index}空指针异常")
                    }
                }
            }
        }
    }.join()
}