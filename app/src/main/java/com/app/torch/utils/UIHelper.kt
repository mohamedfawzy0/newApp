package com.app.torch.utils

import android.widget.TextView
import com.app.torch.R
import com.app.torch.utils.enums.LanguageCode

object UIHelper {
    fun bindCurrentLanguage(textView: TextView) {
        when (LocaleHelper.getLanguage(textView.context)) {
            LanguageCode.AR.code -> {
                textView.setText(R.string.str_language_arabic)
            }
            LanguageCode.EN.code -> {
                textView.setText(R.string.str_language_english)
            }
        }
    }
}