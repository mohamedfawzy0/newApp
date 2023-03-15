package com.app.torch.models.request

data class LoginBody(
    var mobile: String = "",
    var password: String = "",
    var fcmToken: String = ""
)