package com.mei.ktexception

import kotlinx.coroutines.*
import kotlin.jvm.Throws

/**
 * @date 2022/8/8
 * @author mxb
 * @desc
 * @desired
 */
fun main() = runBlocking<Unit> {

    val scope = CoroutineScope(SupervisorJob())
    val job = scope.launch {
        println("根协程job=${coroutineContext[Job]}")
        try {
            launch {
                doWork()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    joinAll(job)

}

@Throws(Exception::class)
private fun doWork():Int{
    return 1/0
}