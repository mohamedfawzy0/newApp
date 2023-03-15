package com.app.torch.models.request

import okhttp3.MultipartBody

data class RegisterBody(
        var type: String = "",
        var fullName: String = "",
        var mobile: String = "",
        var email: String = "",
        var gender: String = "",
        var password: String = "",
        var confirmPassword: String = "",
        var address: String? = "",
        var image: MultipartBody.Part? = null,
        var fcmToken: String = "",
        var cityId :Int = -1
)