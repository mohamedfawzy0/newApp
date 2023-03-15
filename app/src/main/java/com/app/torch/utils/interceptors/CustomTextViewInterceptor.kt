package com.app.torch.utils.interceptors

import com.app.torch.utils.extensions.textview.CustomTextView
import io.github.inflationx.viewpump.InflateRequest
import io.github.inflationx.viewpump.InflateResult
import io.github.inflationx.viewpump.Interceptor

class CustomTextViewInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): InflateResult {
        val request: InflateRequest = chain.request()
        return if (request.name.endsWith("TextView")) {
            val view = CustomTextView(request.context, request.attrs)
            InflateResult.builder()
                .view(view)
                .name(view.javaClass.getName())
                .context(request.context)
                .attrs(request.attrs)
                .build()
        } else {
            chain.proceed(request)
        }
    }
}