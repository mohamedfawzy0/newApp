package com.app.torch

import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val format = SimpleDateFormat("dd/mm/yyyy")

        val calendar = Calendar.getInstance()

        System.out.println(calendar.time.toString())
        System.out.println(format.format(calendar.time))
    }
}
