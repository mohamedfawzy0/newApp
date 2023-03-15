package com.app.torch.utils

import com.app.torch.models.response.Message


sealed class ResponseResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResponseResult<T>()
    data class Failure(val message: Message) : ResponseResult<Nothing>()
    data class UnAuthorized(val message: Message) : ResponseResult<Nothing>()
}