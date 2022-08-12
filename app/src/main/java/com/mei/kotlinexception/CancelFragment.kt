package com.mei.kotlinexception

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CancelFragment : Fragment() {
    companion object {
        private const val TAG = "CancelFragment"
    }

    lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cancel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.tvData)
        view.findViewById<View>(R.id.btn_get_data).setOnClickListener {
            getData()
        }
    }


    private fun getData() {
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