package com.app.torch.utils.managers

import javax.inject.Inject

interface ValidatorInterface {
    fun isPasswordStrong(password: String): Boolean
    fun isEmailValid(email: String): Boolean
}

class Validator @Inject constructor() : ValidatorInterface {
    override fun isPasswordStrong(password: String): Boolean {
        return password.matches("^(?=.*[A-Za-z])(?=.*[0-9]).{8,}\$".toRegex())
    }

    override fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}