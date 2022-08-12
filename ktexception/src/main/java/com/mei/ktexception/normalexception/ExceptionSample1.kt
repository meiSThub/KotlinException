package com.mei.ktexception.normalexception

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.NullPointerException

/**
 * @date 2022/8/10
 * @author mxb
 * @desc
 * @desired
 */

fun main() = runBlocking {
    val scope = CoroutineScope(Job())
    scope.launch {
        try {
            throw NullPointerException("null pointer exception")
        } catch (e: Exception) {
            println("caught ${e.message}")
        }
    }.join()
}