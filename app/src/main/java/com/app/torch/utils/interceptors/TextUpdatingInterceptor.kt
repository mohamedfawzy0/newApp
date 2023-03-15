package com.app.torch.utils.interceptors

import android.widget.TextView
import io.github.inflationx.viewpump.InflateResult
import io.github.inflationx.viewpump.Interceptor

class TextUpdatingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): InflateResult {
        val result: InflateResult = chain.proceed(chain.request())
        if (result.view is TextView) {
            // Do something to result.view()
            // You have access to result.context() and result.attrs()
            val textView = result.view as TextView?
            textView!!.text = textView.text
        }
        return result
    }
}