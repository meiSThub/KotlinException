package com.mei.ktexception

import kotlinx.coroutines.*

/**
 * @date 2022/8/8
 * @author mxb
 * @desc 测试Job的父子关系
 * @desired
 */
fun main() = runBlocking<Unit> {
    val rootJob = Job()
    println("rootJob=$rootJob")
    val scope = CoroutineScope(rootJob)
    val job = scope.launch {
        println("根协程job=${coroutineContext[Job]}")
        rootJob.children.forEach {
            println("rootJob=$rootJob,rootJob child job=$it")
        }
        async {

        }
    }
    joinAll(job)
}