package com.mei.ktexception.supervisorScope

import kotlinx.coroutines.*
import java.lang.NullPointerException

/**
 * @date 2022/8/10
 * @author mxb
 * @desc
 * @desired
 */
fun main() = runBlocking<Unit> {
    /**
     * 使用 SupervisorJob 创建一个 CoroutineScope 并使用此范围调用指定的挂起块。提供的作用域从外部作用域继承其
     * coroutineContext，但用 SupervisorJob 覆盖上下文的作业。一个孩子的失败不会导致这个范围失败，
     * 也不会影响它的其他孩子，因此可以实现处理其孩子失败的自定义策略。有关详细信息，请参阅主管工作。
     * 范围本身的故障（块中抛出的异常或取消）使范围及其所有子级都失败，但不会取消父作业。
     */
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    supervisorScope {
        launch {
            println("first child throw exception")
            throw NullPointerException("空指针")
        }

        launch(CoroutineExceptionHandler { _, throwable ->
            println("第二个子协程:$throwable")
        }) {
            delay(1000)
            println("second child throw exception")
            throw  AssertionError("assert")
        }
    }

    // supervisorScope 方法的含义
    // 通过 SupervisorJob,创建一个 CoroutineScope，并调用传入的方法，
    // example()
}

private suspend fun example() {
    try {
        val supervisorJob = SupervisorJob()
        val scope = CoroutineScope(supervisorJob)
        scope.supervisorScopeBlock()
    } catch (e: Exception) {
        println("Caught an assertion error")
    }
}

private suspend fun CoroutineScope.supervisorScopeBlock(): Any {
    val child = launch {
        try {
            println("the child is sleeping")
            delay(Long.MAX_VALUE)
        } finally {
            println("the child is cacelled")
        }
    }
    yield()
    println("Throwing an exception from the scope")
    throw  AssertionError()
}