package com.mei.kotlinexception

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CancelActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "CancelActivity"
    }

    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancel)
        textView = findViewById(R.id.tvData)
    }

    fun getData(view: View) {
        lifecycleScope.launch {
            try {
                val weather = doHardWork()
                textView.text = weather
            } catch (e: Exception) {
                Log.i(TAG, "getData: textView=$textView")
                textView.text = e.message
            }
        }
    }

    /**
     * 模拟耗时的网络请求，获取天气
     * @return
     */
    // suspend fun doHardWork(): String = withContext(Dispatchers.IO) {
    //     delay(5000)
    //     "今天天气晴朗"
    // }

    suspend fun doHardWork(): String {
        delay(5000)
        return "今天天气晴朗"
    }
}