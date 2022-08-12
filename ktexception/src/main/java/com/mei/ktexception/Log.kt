package com.mei.ktexception

/**
 * @date 2022/8/8
 * @author mxb
 * @desc
 * @desired
 */
class Log {
    companion object {
        fun d(tag: String, info: String) {
            println("$tag: $info")
        }
    }
}