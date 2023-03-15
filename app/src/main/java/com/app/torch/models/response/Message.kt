package com.app.torch.models.response

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("message")
    var errorMessage: String = "",
    var statusCode: Int = 0,
    var viewIndex: Int = -1
)