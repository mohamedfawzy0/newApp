package com.app.torch.utils.extensions.string

import android.content.Context
import com.app.torch.utils.LocaleHelper
import com.app.torch.utils.enums.LanguageCode
import java.text.SimpleDateFormat

fun String.convertToArabicDigits(): String {
    return replace("1".toRegex(), "١").replace("2".toRegex(), "٢")
        .replace("3".toRegex(), "٣").replace("4".toRegex(), "٤")
        .replace("5".toRegex(), "٥").replace("6".toRegex(), "٦")
        .replace("7".toRegex(), "٧").replace("8".toRegex(), "٨")
        .replace("9".toRegex(), "٩").replace("0".toRegex(), "٠")
}

fun String.formatTime(): String {
    try {
        val fromFormat = SimpleDateFormat("HH:mm:ss")
        val toFormat = SimpleDateFormat("hh:mm a")

        return toFormat.format(fromFormat.parse(this))
    } catch (e: Exception) {
    }
    return this
}

fun String.formatPrice(context: Context): String {
    val lang = LocaleHelper.getLanguage(context)
    var currency = "ssss"
    var price = ""
    lang?.let {
        when (it) {
            LanguageCode.AR.code -> {
                price = convertToArabicDigits()
            }
            LanguageCode.EN.code -> {
                price = this
            }
        }
    }

    return price.plus(" ").plus(currency)
}


fun String.formatText(context: Context): String {
    val lang = LocaleHelper.getLanguage(context)
    var price = ""
    lang?.let {
        when (it) {
            LanguageCode.AR.code -> {
                price = convertToArabicDigits()
            }
            LanguageCode.EN.code -> {
                price = this
            }
        }
    }

    return price
}