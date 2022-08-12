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
    // GlobalScope.launch {
    //     throw NullPointerException("空指针")
    // }.join()

    val job = Job()
    println("job=$job")
    val supervisorJob = SupervisorJob()
    // val scope = CoroutineScope(job)

    println("supervisorJob=$supervisorJob")
    val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        // throwable.printStackTrace()
        println("异常捕获：${throwable.message}")
    }
    val scope = CoroutineScope(supervisorJob)
    // scope.launch(supervisorJob) {
    scope.launch() {
        println("根Job=${coroutineContext[Job]}")
        supervisorJob.children.forEach {
            println("parent supervisorJob=$supervisorJob,$it")
        }
        launch {
            println("child1 job=${coroutineContext[Job]}")
            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("发生异常，被动取消,isActive=$isActive")
            }
        }
        coroutineContext[Job]?.children?.forEach {
            println("parent=${coroutineContext[Job]},child=$it")
        }
        launch {
            println("child2 job=${coroutineContext[Job]}")
            val num = 1 / 0
        }
        println("job children=${job.children.iterator().hasNext()}")
        job.children.forEach {
            println("rootJob=$job,$it")
        }
    }.join()
}